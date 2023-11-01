package tw.com.deathhit.data.photo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tw.com.deathhit.core.app_database.AppDatabase
import tw.com.deathhit.core.unsplash_api.UnsplashService
import tw.com.deathhit.domain.PhotoRepository
import tw.com.deathhit.domain.PlantRepository
import tw.com.deathhit.domain.model.PhotoDO

@OptIn(ExperimentalPagingApi::class)
class PhotoRepositoryImp(
    private val appDatabase: AppDatabase,
    private val plantRepository: PlantRepository,
    private val unsplashService: UnsplashService
) : PhotoRepository {
    private val photoItemDao = appDatabase.photoItemDao()
    override fun getPhotoPagingDataFlow(plantId: String): Flow<PagingData<PhotoDO>> = Pager(
        config = PagingConfig(pageSize = 25),
        remoteMediator = PhotoRemoteMediator(
            appDatabase = appDatabase,
            plantId = plantId,
            plantRepository = plantRepository,
            unsplashService = unsplashService
        )
    ) {
        photoItemDao.getEntitiesPagingSource(plantId = plantId)
    }.flow.map { pagingData ->
        pagingData.map {
            it.toPhotoDO(
                attributionUrl = unsplashService.getAttributionUrl(authorId = it.authorId)
            )
        }
    }
}