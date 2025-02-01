package tw.com.deathhit.data.garden_planting_kmp

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tw.com.deathhit.core.sunflower_clone_database_kmp.SunflowerCloneDatabase
import tw.com.deathhit.core.sunflower_clone_database_kmp.gardenPlantingDao
import tw.com.deathhit.core.sunflower_clone_database_kmp.gardenPlantingItemDao
import tw.com.deathhit.core.sunflowerclonedatabasekmp.GardenPlantingEntity
import tw.com.deathhit.domain.GardenPlantingRepository
import tw.com.deathhit.domain.model.GardenPlantingDO

class GardenPlantingRepositoryImp(
    sunflowerCloneDatabase: SunflowerCloneDatabase
) : GardenPlantingRepository {
    private val gardenPlantingDao = sunflowerCloneDatabase.gardenPlantingDao()
    private val gardenPlantingItemDao = sunflowerCloneDatabase.gardenPlantingItemDao()

    override suspend fun addGardenPlanting(plantId: String) {
        gardenPlantingDao.upsert(
            listOf(GardenPlantingEntity(
                plant_id = plantId,
                garden_planting_id = 0,
                plant_date = System.currentTimeMillis()
            ))
        )
    }

    override fun getGardenPlantingPagingDataFlow(): Flow<PagingData<GardenPlantingDO>> = Pager(
        config = PagingConfig(pageSize = 25)
    ) {
        gardenPlantingItemDao.getEntitiesPagingSource()
    }.flow.map { pagingData -> pagingData.map { it.toGardenPlantingDO() } }
}