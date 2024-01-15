package tw.com.deathhit.feature.garden_planting_list_compose.config

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.testing.asPagingSourceFactory
import kotlinx.coroutines.flow.Flow
import tw.com.deathhit.domain.GardenPlantingRepository
import tw.com.deathhit.domain.model.GardenPlantingDO

class TestGardenPlantingRepository : GardenPlantingRepository {
    private val mutableList = mutableListOf<GardenPlantingDO>()

    override suspend fun addGardenPlanting(plantId: String) {
        mutableList.add(
            generateGardenPlantingDO(
                gardenPlantingId = mutableList.size,
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