package tw.com.deathhit.data.photo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tw.com.deathhit.core.sunflower_clone.app_database.SunflowerCloneDatabase
import tw.com.deathhit.core.unsplash.api_client.UnsplashService
import tw.com.deathhit.domain.PhotoRepository
import tw.com.deathhit.domain.model.PhotoDO

@OptIn(ExperimentalPagingApi::class)
class PhotoRepositoryImp(
    private val sunflowerCloneDatabase: SunflowerCloneDatabase,
    private val unsplashService: UnsplashService
) : PhotoRepository {
    private val photoItemDao = sunflowerCloneDatabase.photoItemDao()
    override fun getPhotoPagingDataFlow(plantName: String): Flow<PagingData<PhotoDO>> = Pager(
        config = PagingConfig(pageSize = 25),
        remoteMediator = PhotoRemoteMediator(
            plantName = plantName,
            sunflowerCloneDatabase = sunflowerCloneDatabase,
            unsplashService = unsplashService
        )
    ) {
        photoItemDao.getEntitiesPagingSource(plantName = plantName)
    }.flow.map { pagingData ->
        pagingData.map {
            it.toPhotoDO(
                attributionUrl = unsplashService.getAttributionUrl(userName = it.authorId)
            )
        }
    }
}