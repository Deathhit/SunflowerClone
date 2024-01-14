package tw.com.deathhit.feature.plant_list_compose.config

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.testing.asPagingSourceFactory
import kotlinx.coroutines.flow.Flow
import tw.com.deathhit.domain.PlantRepository
import tw.com.deathhit.domain.model.PlantDO

class TestPlantRepository : PlantRepository {
    private val list = generatePlantDOs()

    override fun getPlantFlow(plantId: String): Flow<PlantDO?> {
        throw UnsupportedOperationException()
    }

    override fun getPlantPagingDataFlow(): Flow<PagingData<PlantDO>> = Pager(
        config = PagingConfig(25),
        pagingSourceFactory = list.asPagingSourceFactory()
    ).flow
}