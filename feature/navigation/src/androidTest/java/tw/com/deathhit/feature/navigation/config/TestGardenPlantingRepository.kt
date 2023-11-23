package tw.com.deathhit.feature.navigation.config

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tw.com.deathhit.domain.GardenPlantingRepository
import tw.com.deathhit.domain.model.GardenPlantingDO

class TestGardenPlantingRepository : GardenPlantingRepository {
    override suspend fun addGardenPlanting(plantId: String) {
        throw UnsupportedOperationException()
    }

    override fun getGardenPlantingPagingDataFlow(): Flow<PagingData<GardenPlantingDO>> =
        throw UnsupportedOperationException()
}