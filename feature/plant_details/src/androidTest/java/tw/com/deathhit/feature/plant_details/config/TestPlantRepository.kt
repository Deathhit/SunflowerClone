package tw.com.deathhit.feature.plant_details.config

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tw.com.deathhit.domain.PlantRepository
import tw.com.deathhit.domain.model.PlantDO

class TestPlantRepository: PlantRepository {
    override fun getPlantFlow(plantId: String): Flow<PlantDO?> {
        TODO()
    }

    override fun getPlantPagingDataFlow(): Flow<PagingData<PlantDO>> {
        TODO()
    }
}