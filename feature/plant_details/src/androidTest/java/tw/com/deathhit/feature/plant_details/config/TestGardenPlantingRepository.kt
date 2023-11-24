package tw.com.deathhit.feature.plant_details.config

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tw.com.deathhit.domain.GardenPlantingRepository
import tw.com.deathhit.domain.model.GardenPlantingDO

class TestGardenPlantingRepository : GardenPlantingRepository {
    val plantIds = mutableListOf<String>()

    override suspend fun addGardenPlanting(plantId: String) {
        plantIds.add(plantId)
    }

    override fun getGardenPlantingPagingDataFlow(): Flow<PagingData<GardenPlantingDO>> {
        throw UnsupportedOperationException()
    }
}