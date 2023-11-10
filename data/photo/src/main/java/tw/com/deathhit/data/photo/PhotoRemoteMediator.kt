package tw.com.deathhit.data.photo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import tw.com.deathhit.core.app_database.AppDatabase
import tw.com.deathhit.core.app_database.entity.PhotoRemoteKeysEntity
import tw.com.deathhit.core.app_database.view.PhotoItemView
import tw.com.deathhit.core.unsplash_api.UnsplashService

@OptIn(ExperimentalPagingApi::class)
class PhotoRemoteMediator(
    private val appDatabase: AppDatabase,
    private val plantName: String,
    private val unsplashService: UnsplashService
) : RemoteMediator<Int, PhotoItemView>() {
    private val photoDao = appDatabase.photoDao()
    private val photoRemoteKeysDao = appDatabase.photoRemoteKeysDao()
    private val photoRemoteOrderDao = appDatabase.photoRemoteOrderDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PhotoItemView>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeysClosestToCurrentPosition(state)

                    remoteKeys?.nextKey?.minus(1) ?: FIRST_PAGE
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstItem(state)
                    // If remoteKey is null, that means the refresh result is not in the database yet.
                    // We can return Success with 'endOfPaginationReached = false' because Paging
                    // will call this method again if RemoteKeys becomes non-null.
                    // If remoteKeys is NOT NULL but its previousKey is null, that means we've reached
                    // the end of pagination for prepend.
                    val previousKey = remoteKeys?.previousKey
                        ?: return MediatorResult.Success(remoteKeys != null)

                    previousKey
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeysForLastItem(state)
                    // If remoteKey is null, that means the refresh result is not in the database yet.
                    // We can return Success with 'endOfPaginationReached = false' because Paging
                    // will call this method again if RemoteKeys becomes non-null.
                    // If remoteKeys is NOT NULL but its nextKey is null, that means we've reached
                    // the end of pagination for append.
                    val nextKey =
                        remoteKeys?.nextKey ?: return MediatorResult.Success(
                            remoteKeys != null
                        )

                    nextKey
                }
            }

            // Suspending network load via Retrofit. This doesn't need to
            // be wrapped in a withContext(Dispatcher.IO) { ... } block
            // since Retrofit's Coroutine CallAdapter dispatches on a
            // worker thread.
            val itemList = getRemoteItems(page = loadKey, pageSize = state.config.pageSize)

            saveRemoteItems(itemList = itemList, loadKey = loadKey, loadType = loadType)

            MediatorResult.Success(endOfPaginationReached = itemList.isEmpty())
        } catch (e: Throwable) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteItems(page: Int, pageSize: Int): List<PhotoRemoteItems> =
        unsplashService.searchPhotos(page = page, perPage = pageSize, query = plantName)
            .toPhotoRemoteItem(page = page, pageSize = pageSize, plantName = plantName)

    private suspend fun getRemoteKeysClosestToCurrentPosition(
        state: PagingState<Int, PhotoItemView>
    ) = with(state) {
        anchorPosition?.let { closestItemToPosition(it) }
            ?.let {
                photoRemoteKeysDao.get(photoId = it.photoId)
            }
    }

    private suspend fun getRemoteKeysForFirstItem(
        state: PagingState<Int, PhotoItemView>
    ) = with(state) {
        firstItemOrNull()?.let {
            photoRemoteKeysDao.get(photoId = it.photoId)
        }
    }

    private suspend fun getRemoteKeysForLastItem(
        state: PagingState<Int, PhotoItemView>
    ) = with(state) {
        lastItemOrNull()?.let {
            photoRemoteKeysDao.get(photoId = it.photoId)
        }
    }

    private suspend fun saveRemoteItems(
        itemList: List<PhotoRemoteItems>,
        loadKey: Int,
        loadType: LoadType
    ) {
        val nextKey = loadKey + 1
        val previousKey = if (loadKey == FIRST_PAGE) null else loadKey - 1

        appDatabase.withTransaction {
            if (loadType == LoadType.REFRESH)
                photoDao.clearByPlantName(plantName = plantName)

            //Upsert the master entities.
            photoDao.upsert(entities = itemList.map { it.photoEntity })

            //Upsert the slave entities.
            photoRemoteKeysDao.upsert(entities = itemList.map {
                PhotoRemoteKeysEntity(
                    nextKey = nextKey,
                    photoId = it.photoEntity.photoId,
                    previousKey = previousKey
                )
            })
            photoRemoteOrderDao.upsert(entities = itemList.map { it.photoRemoteOrderEntity })
        }
    }

    companion object {
        private const val FIRST_PAGE = 1
    }
}