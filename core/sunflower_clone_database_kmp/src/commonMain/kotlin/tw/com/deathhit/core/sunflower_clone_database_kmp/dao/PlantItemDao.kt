package tw.com.deathhit.core.sunflower_clone_database_kmp.dao

import androidx.paging.PagingSource
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOneOrNull
import app.cash.sqldelight.paging3.QueryPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import tw.com.deathhit.core.sunflower_clone_database_kmp.SunflowerCloneDatabase
import tw.com.deathhit.core.sunflowerclonedatabasekmp.PlantItemView

interface PlantItemDao {
    fun getEntityFlow(plantId: String): Flow<PlantItemView?>

    fun getEntitiesPagingSource(): PagingSource<Int, PlantItemView>

    class Imp(database: SunflowerCloneDatabase) : PlantItemDao {
        private val queries = database.plantItemViewQueries

        override fun getEntityFlow(plantId: String): Flow<PlantItemView?> =
            queries.getEntitiesFlow(plantId = plantId).asFlow().mapToOneOrNull(Dispatchers.IO)

        override fun getEntitiesPagingSource(): PagingSource<Int, PlantItemView> =
            QueryPagingSource(
                countQuery = queries.count(),
                transacter = queries,
                context = Dispatchers.IO,
                queryProvider = queries::getEntitiesPagingSource
            )
    }
}