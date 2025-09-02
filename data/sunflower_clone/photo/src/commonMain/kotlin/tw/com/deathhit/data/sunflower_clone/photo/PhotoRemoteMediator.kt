package tw.com.deathhit.data.sunflower_clone.photo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import tw.com.deathhit.core.sunflower_clone.app_database.SunflowerCloneDatabase
import tw.com.deathhit.core.sunflower_clone.app_database.view.PhotoItemView
import tw.com.deathhit.core.unsplash.api_client.UnsplashService

@OptIn(ExperimentalPagingApi::class)
class PhotoRemoteMediator(
    private val plantName: String,
    sunflowerCloneDatabase: SunflowerCloneDatabase,
    private val unsplashService: UnsplashService
) : RemoteMediator<Int, PhotoItemView>() {
    private val photoDao = sunflowerCloneDatabase.photoDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PhotoItemView>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeysClosestToCurrentPosition(state)

                    remoteKeys?.nextKey?.minus(1) ?: 1
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

            if (loadType == LoadType.REFRESH)
                photoDao.clear()

            // Suspending network load via Retrofit. This doesn't need to
            // be wrapped in a withContext(Dispatcher.IO) { ... } block
            // since Retrofit's Coroutine CallAdapter dispatches on a
            // worker thread.
            val itemList = getRemoteItems(page = loadKey, pageSize = state.config.pageSize)

            MediatorResult.Success(endOfPaginationReached = itemList.isEmpty())
        } catch (e: Throwable) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteItems(page: Int, pageSize: Int) =
        unsplashService.searchPhotos(
            page = page,
            perPage = pageSize,
            query = plantName
        ).map {
            it.toPhotoEntity(plantName = plantName)
        }.also {
            photoDao.upsertPhotoPage(
                entities = it,
                page = page,
                pageSize = pageSize
            )
        }

    private suspend fun getRemoteKeysClosestToCurrentPosition(
        state: PagingState<Int, PhotoItemView>
    ) = with(state) {
        anchorPosition?.let { closestItemToPosition(it) }
            ?.let {
                photoDao.getRemoteKeys(photoId = it.photoId)
            }
    }


    private suspend fun getRemoteKeysForFirstItem(
        state: PagingState<Int, PhotoItemView>
    ) = with(state) {
        firstItemOrNull()?.let {
            photoDao.getRemoteKeys(photoId = it.photoId)
        }
    }

    private suspend fun getRemoteKeysForLastItem(
        state: PagingState<Int, PhotoItemView>
    ) = with(state) {
        lastItemOrNull()?.let {
            photoDao.getRemoteKeys(photoId = it.photoId)
        }
    }
}