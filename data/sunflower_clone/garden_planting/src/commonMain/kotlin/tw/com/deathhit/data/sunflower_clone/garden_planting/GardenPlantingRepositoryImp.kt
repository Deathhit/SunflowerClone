package tw.com.deathhit.data.sunflower_clone.garden_planting

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tw.com.deathhit.core.sunflower_clone.app_database.SunflowerCloneDatabase
import tw.com.deathhit.core.sunflower_clone.app_database.entity.GardenPlantingEntity
import tw.com.deathhit.domain.sunflower_clone.GardenPlantingRepository
import tw.com.deathhit.domain.sunflower_clone.model.GardenPlantingDO

class GardenPlantingRepositoryImp(
    sunflowerCloneDatabase: SunflowerCloneDatabase
) : GardenPlantingRepository {
    private val gardenPlantingDao = sunflowerCloneDatabase.gardenPlantingDao()
    private val gardenPlantingItemDao = sunflowerCloneDatabase.gardenPlantingItemDao()

    override suspend fun addGardenPlanting(plantDate: Long, plantId: String) {
        gardenPlantingDao.upsert(
            listOf(GardenPlantingEntity(plantDate = plantDate, plantId = plantId))
        )
    }

    override fun getGardenPlantingPagingDataFlow(): Flow<PagingData<GardenPlantingDO>> = Pager(
        config = PagingConfig(pageSize = 25)
    ) {
        gardenPlantingItemDao.getEntitiesPagingSource()
    }.flow.map { pagingData -> pagingData.map { it.toGardenPlantingDO() } }
}