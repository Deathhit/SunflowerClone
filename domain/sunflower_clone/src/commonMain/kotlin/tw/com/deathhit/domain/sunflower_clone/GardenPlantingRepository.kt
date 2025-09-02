package tw.com.deathhit.domain.sunflower_clone

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tw.com.deathhit.domain.sunflower_clone.model.GardenPlantingDO

interface GardenPlantingRepository {
    suspend fun addGardenPlanting(plantDate: Long, plantId: String)
    fun getGardenPlantingPagingDataFlow(): Flow<PagingData<GardenPlantingDO>>
}