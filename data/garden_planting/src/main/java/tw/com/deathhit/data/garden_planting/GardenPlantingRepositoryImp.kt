package tw.com.deathhit.data.garden_planting

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tw.com.deathhit.core.app_database.AppDatabase
import tw.com.deathhit.core.app_database.entity.GardenPlantingEntity
import tw.com.deathhit.domain.GardenPlantingRepository
import tw.com.deathhit.domain.model.GardenPlantingDO

class GardenPlantingRepositoryImp(
    appDatabase: AppDatabase
) : GardenPlantingRepository {
    private val gardenPlantingDao = appDatabase.gardenPlantingDao()
    private val gardenPlantingItemDao = appDatabase.gardenPlantingItemDao()

    override suspend fun addGardenPlanting(plantId: String) {
        gardenPlantingDao.upsert(
            listOf(GardenPlantingEntity(plantId = plantId))
        )
    }

    override fun getGardenPlantingPagingDataFlow(): Flow<PagingData<GardenPlantingDO>> = Pager(
        config = PagingConfig(pageSize = 25)
    ) {
        gardenPlantingItemDao.getEntitiesPagingSource()
    }.flow.map { pagingData -> pagingData.map { it.toGardenPlantingDO() } }
}