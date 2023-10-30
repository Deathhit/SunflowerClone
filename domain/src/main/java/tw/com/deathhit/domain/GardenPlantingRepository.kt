package tw.com.deathhit.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tw.com.deathhit.domain.model.GardenPlantingDO

interface GardenPlantingRepository {
    suspend fun addGardenPlanting(plantId: String)
    fun getGardenPlantingPagingDataFlow(): Flow<PagingData<GardenPlantingDO>>
}