package tw.com.deathhit.feature.plant_details.config

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tw.com.deathhit.domain.GardenPlantingRepository
import tw.com.deathhit.domain.model.GardenPlantingDO

class TestGardenPlantingRepository : GardenPlantingRepository {
    override suspend fun addGardenPlanting(plantId: String) {
        //todo
    }

    override fun getGardenPlantingPagingDataFlow(): Flow<PagingData<GardenPlantingDO>> =
        TODO()
}