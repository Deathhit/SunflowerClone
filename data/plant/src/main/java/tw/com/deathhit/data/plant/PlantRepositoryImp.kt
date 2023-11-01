package tw.com.deathhit.data.plant

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tw.com.deathhit.core.app_database.AppDatabase
import tw.com.deathhit.domain.PlantRepository
import tw.com.deathhit.domain.model.PlantDO

class PlantRepositoryImp(appDatabase: AppDatabase) : PlantRepository {
    private val plantItemDao = appDatabase.plantItemDao()

    override suspend fun getPlant(plantId: String): PlantDO? =
        plantItemDao.getEntity(plantId = plantId)?.toPlantDO()

    override fun getPlantPagingDataFlow(): Flow<PagingData<PlantDO>> = Pager(
        config = PagingConfig(pageSize = 25)
    ) {
        plantItemDao.getEntitiesPagingSource()
    }.flow.map { pagingData -> pagingData.map { it.toPlantDO() } }
}