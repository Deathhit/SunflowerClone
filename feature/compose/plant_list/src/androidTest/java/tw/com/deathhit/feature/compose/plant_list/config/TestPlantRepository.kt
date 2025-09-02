package tw.com.deathhit.feature.compose.plant_list.config

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.testing.asPagingSourceFactory
import kotlinx.coroutines.flow.Flow
import tw.com.deathhit.domain.sunflower_clone.PlantRepository
import tw.com.deathhit.domain.sunflower_clone.model.PlantDO

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