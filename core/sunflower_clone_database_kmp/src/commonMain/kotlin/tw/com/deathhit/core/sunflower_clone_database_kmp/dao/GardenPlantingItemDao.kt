package tw.com.deathhit.core.sunflower_clone_database_kmp.dao

import androidx.paging.PagingSource
import app.cash.sqldelight.paging3.QueryPagingSource
import kotlinx.coroutines.Dispatchers
import tw.com.deathhit.core.sunflower_clone_database_kmp.SunflowerCloneDatabase
import tw.com.deathhit.core.sunflowerclonedatabasekmp.GardenPlantingItemView

interface GardenPlantingItemDao {
    fun getEntitiesPagingSource(): PagingSource<Int, GardenPlantingItemView>

    class Imp(database: SunflowerCloneDatabase) : GardenPlantingItemDao {
        private val queries = database.gardenPlantingItemViewQueries

        override fun getEntitiesPagingSource(): PagingSource<Int, GardenPlantingItemView> =
            QueryPagingSource(
                countQuery = queries.count(),
                transacter = queries,
                context = Dispatchers.IO,
                queryProvider = queries::getEntitiesPagingSource
            )
    }
}