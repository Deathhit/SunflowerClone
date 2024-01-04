package tw.com.deathhit.feature.plant_details_compose.config

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import tw.com.deathhit.domain.PlantRepository
import tw.com.deathhit.domain.model.PlantDO

class TestPlantRepository : PlantRepository {
    private val plantMap = mutableMapOf<String, PlantDO>()

    override fun getPlantFlow(plantId: String): Flow<PlantDO?> = flowOf(plantMap.getOrPut(plantId) {
        generatePlantDO(plantId)
    })

    override fun getPlantPagingDataFlow(): Flow<PagingData<PlantDO>> {
        throw UnsupportedOperationException()
    }
}