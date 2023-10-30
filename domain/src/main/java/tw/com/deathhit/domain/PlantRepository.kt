package tw.com.deathhit.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tw.com.deathhit.domain.model.PlantDO

interface PlantRepository {
    suspend fun getPlant(plantId: String): PlantDO?
    fun getPlantPagingDataFlow(): Flow<PagingData<PlantDO>>
}