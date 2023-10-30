package tw.com.deathhit.data.plant

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tw.com.deathhit.core.app_database.AppDatabase
import tw.com.deathhit.domain.PlantRepository
import tw.com.deathhit.domain.model.PlantDO

internal class PlantRepositoryImp(appDatabase: AppDatabase) : PlantRepository {
    private val plantItemDao = appDatabase.plantItemDao()

    override suspend fun getPlant(plantId: String): PlantDO? {
        TODO("Not yet implemented")
    }

    override fun getPlantPagingDataFlow(): Flow<PagingData<PlantDO>> = plantItemDao.getEntitiesPagingSource()
}