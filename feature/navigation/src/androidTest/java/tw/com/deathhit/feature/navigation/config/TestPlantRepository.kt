package tw.com.deathhit.feature.navigation.config

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tw.com.deathhit.domain.PlantRepository
import tw.com.deathhit.domain.model.PlantDO

class TestPlantRepository: PlantRepository {
    override fun getPlantFlow(plantId: String): Flow<PlantDO?> {
        throw UnsupportedOperationException()
    }

    override fun getPlantPagingDataFlow(): Flow<PagingData<PlantDO>> {
        throw UnsupportedOperationException()
    }
}