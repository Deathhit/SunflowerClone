package tw.com.deathhit.data.sunflower_clone.plant

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tw.com.deathhit.core.sunflower_clone.app_database.SunflowerCloneDatabase
import tw.com.deathhit.domain.sunflower_clone.PlantRepository
import tw.com.deathhit.domain.sunflower_clone.model.PlantDO

class PlantRepositoryImp(sunflowerCloneDatabase: SunflowerCloneDatabase) : PlantRepository {
    private val plantItemDao = sunflowerCloneDatabase.plantItemDao()

    override fun getPlantFlow(plantId: String): Flow<PlantDO?> =
        plantItemDao.getEntityFlow(plantId = plantId).map { it?.toPlantDO() }

    override fun getPlantPagingDataFlow(): Flow<PagingData<PlantDO>> = Pager(
        config = PagingConfig(pageSize = 25)
    ) {
        plantItemDao.getEntitiesPagingSource()
    }.flow.map { pagingData -> pagingData.map { it.toPlantDO() } }
}