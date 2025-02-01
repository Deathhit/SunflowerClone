package tw.com.deathhit.core.sunflower_clone_database_kmp.dao

import androidx.paging.PagingSource
import app.cash.sqldelight.paging3.QueryPagingSource
import kotlinx.coroutines.Dispatchers
import tw.com.deathhit.core.sunflower_clone_database_kmp.SunflowerCloneDatabase
import tw.com.deathhit.core.sunflowerclonedatabasekmp.PhotoItemView

interface PhotoItemDao {
    fun getEntitiesPagingSource(plantName: String): PagingSource<Int, PhotoItemView>

    class Imp(database: SunflowerCloneDatabase) : PhotoItemDao {
        private val queries = database.photoItemViewQueries

        override fun getEntitiesPagingSource(plantName: String): PagingSource<Int, PhotoItemView> =
            QueryPagingSource(
                countQuery = queries.count(),
                transacter = queries,
                context = Dispatchers.IO,
                queryProvider = { limit, offset ->
                    queries.getEntitiesPagingSource(
                        plant_name = plantName,
                        limit = limit,
                        offset = offset
                    )
                }
            )
    }
}