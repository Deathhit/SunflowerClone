package tw.com.deathhit.data.photo_kmp

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import tw.com.deathhit.core.sunflower_clone_database_kmp.SunflowerCloneDatabase
import tw.com.deathhit.core.sunflower_clone_database_kmp.photoDao
import tw.com.deathhit.core.sunflower_clone_database_kmp.photoRemoteKeysDao
import tw.com.deathhit.core.sunflower_clone_database_kmp.photoRemoteOrderDao
import tw.com.deathhit.core.sunflowerclonedatabasekmp.PhotoItemView
import tw.com.deathhit.core.sunflowerclonedatabasekmp.PhotoRemoteKeysEntity
import tw.com.deathhit.core.unsplash_api.UnsplashService
import tw.com.deathhit.data.photo_kmp.model.PhotoRemoteItems

@OptIn(ExperimentalPagingApi::class)
class PhotoRemoteMediator(
    private val plantName: String,
    private val sunflowerCloneDatabase: SunflowerCloneDatabase,
    private val unsplashService: UnsplashService
) : RemoteMediator<Int, PhotoItemView>() {
    private val photoDao = sunflowerCloneDatabase.photoDao()
    private val photoRemoteKeysDao = sunflowerCloneDatabase.photoRemoteKeysDao()
    private val photoRemoteOrderDao = sunflowerCloneDatabase.photoRemoteOrderDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PhotoItemView>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeysClosestToCurrentPosition(state)

                    remoteKeys?.next_key?.minus(1) ?: FIRST_PAGE
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstItem(state)
                    // If remoteKey is null, that means the refresh result is not in the database yet.
                    // We can return Success with 'endOfPaginationReached = false' because Paging
                    // will call this method again if RemoteKeys becomes non-null.
                    // If remoteKeys is NOT NULL but its previousKey is null, that means we've reached
                    // the end of pagination for prepend.
                    val previousKey = remoteKeys?.previous_key
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
                        remoteKeys?.next_key ?: return MediatorResult.Success(
                            remoteKeys != null
                        )

                    nextKey
                }
            }

            // Suspending network load via Retrofit. This doesn't need to
            // be wrapped in a withContext(Dispatcher.IO) { ... } block
            // since Retrofit's Coroutine CallAdapter dispatches on a
            // worker thread.
            val itemList = getRemoteItems(page = loadKey.toInt(), pageSize = state.config.pageSize)

            saveRemoteItems(itemList = itemList, loadKey = loadKey.toInt(), loadType = loadType)

            MediatorResult.Success(endOfPaginationReached = itemList.isEmpty())
        } catch (e: Throwable) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteItems(page: Int, pageSize: Int): List<PhotoRemoteItems> =
        unsplashService.searchPhotos(page = page, perPage = pageSize, query = plantName)
            .toPhotoRemoteItemsList(page = page, pageSize = pageSize, plantName = plantName)

    private suspend fun getRemoteKeysClosestToCurrentPosition(
        state: PagingState<Int, PhotoItemView>
    ) = with(state) {
        anchorPosition?.let { closestItemToPosition(it) }
            ?.let {
                photoRemoteKeysDao.get(photoId = it.photo_id)
            }
    }

    private suspend fun getRemoteKeysForFirstItem(
        state: PagingState<Int, PhotoItemView>
    ) = with(state) {
        firstItemOrNull()?.let {
            photoRemoteKeysDao.get(photoId = it.photo_id)
        }
    }

    private suspend fun getRemoteKeysForLastItem(
        state: PagingState<Int, PhotoItemView>
    ) = with(state) {
        lastItemOrNull()?.let {
            photoRemoteKeysDao.get(photoId = it.photo_id)
        }
    }

    private suspend fun saveRemoteItems(
        itemList: List<PhotoRemoteItems>,
        loadKey: Int,
        loadType: LoadType
    ) {
        val nextKey = loadKey + 1
        val previousKey = if (loadKey == FIRST_PAGE) null else loadKey - 1

        //todo need to support transaction in database

        if (loadType == LoadType.REFRESH)
            photoDao.clearByPlantName(plantName = plantName)

        //Upsert the master entities.
        photoDao.upsert(entities = itemList.map { it.photoEntity })

        //Upsert the slave entities.
        photoRemoteKeysDao.upsert(entities = itemList.map {
            PhotoRemoteKeysEntity(
                next_key = nextKey.toLong(),
                photo_id = it.photoEntity.photo_id,
                previous_key = previousKey?.toLong()
            )
        })
        photoRemoteOrderDao.upsert(entities = itemList.map { it.photoRemoteOrderEntity })

    }

    companion object {
        private const val FIRST_PAGE = 1
    }
}