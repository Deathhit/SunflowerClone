package tw.com.deathhit.core.sunflower_clone_database_kmp.dao

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tw.com.deathhit.core.sunflower_clone_database_kmp.SunflowerCloneDatabase
import tw.com.deathhit.core.sunflowerclonedatabasekmp.PhotoRemoteOrderEntity

interface PhotoRemoteOrderDao {
    suspend fun upsert(entities: List<PhotoRemoteOrderEntity>)

    class Imp(private val database: SunflowerCloneDatabase): PhotoRemoteOrderDao {
        private val queries = database.photoRemoteOrderEntityQueries

        override suspend fun upsert(entities: List<PhotoRemoteOrderEntity>) {
            withContext(Dispatchers.IO) {
                database.transaction {
                    entities.forEach {
                        queries.upsert(
                            photo_id = it.photo_id,
                            remote_order = it.remote_order
                        )
                    }
                }
            }
        }
    }
}