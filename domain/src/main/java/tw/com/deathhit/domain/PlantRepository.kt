package tw.com.deathhit.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tw.com.deathhit.domain.model.PlantDO

interface PlantRepository {
    fun getPlantFlow(plantId: String): Flow<PlantDO?>
    fun getPlantPagingDataFlow(): Flow<PagingData<PlantDO>>
}