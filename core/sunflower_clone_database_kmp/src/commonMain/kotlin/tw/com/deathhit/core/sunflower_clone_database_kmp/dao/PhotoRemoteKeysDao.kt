package tw.com.deathhit.core.sunflower_clone_database_kmp.dao

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tw.com.deathhit.core.sunflower_clone_database_kmp.SunflowerCloneDatabase
import tw.com.deathhit.core.sunflowerclonedatabasekmp.PhotoRemoteKeysEntity

interface PhotoRemoteKeysDao {
    suspend fun get(photoId: String): PhotoRemoteKeysEntity?

    suspend fun upsert(entities: List<PhotoRemoteKeysEntity>)

    class Imp(private val database: SunflowerCloneDatabase) : PhotoRemoteKeysDao {
        private val queries = database.photoRemoteKeysEntityQueries

        override suspend fun get(photoId: String): PhotoRemoteKeysEntity? =
            withContext(Dispatchers.IO) {
                queries.get(photo_id = photoId).executeAsOneOrNull()
            }

        override suspend fun upsert(entities: List<PhotoRemoteKeysEntity>) {
            withContext(Dispatchers.IO) {
                database.transaction {
                    entities.forEach {
                        queries.upsert(
                            photo_id = it.photo_id,
                            next_key = it.next_key,
                            previous_key = it.previous_key
                        )
                    }
                }
            }
        }
    }
}