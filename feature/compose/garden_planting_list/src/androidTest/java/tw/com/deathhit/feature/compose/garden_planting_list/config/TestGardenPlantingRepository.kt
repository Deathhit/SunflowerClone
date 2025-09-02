package tw.com.deathhit.feature.compose.garden_planting_list.config

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.testing.asPagingSourceFactory
import kotlinx.coroutines.flow.Flow
import tw.com.deathhit.domain.sunflower_clone.GardenPlantingRepository
import tw.com.deathhit.domain.sunflower_clone.model.GardenPlantingDO

class TestGardenPlantingRepository : GardenPlantingRepository {
    private val mutableList = mutableListOf<GardenPlantingDO>()

    override suspend fun addGardenPlanting(plantDate: Long, plantId: String) {
        mutableList.add(
            generateGardenPlantingDO(
                gardenPlantingId = mutableList.size,
                plantDate = plantDate,
                plantId = plantId
            )
        )
    }

    override fun getGardenPlantingPagingDataFlow(): Flow<PagingData<GardenPlantingDO>> =
        Pager(
            config = PagingConfig(25),
            pagingSourceFactory = mutableList.asPagingSourceFactory()
        ).flow
}