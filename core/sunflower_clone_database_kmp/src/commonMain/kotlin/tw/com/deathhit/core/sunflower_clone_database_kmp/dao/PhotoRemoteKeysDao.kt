package tw.com.deathhit.core.sunflower_clone_database_kmp.dao

import tw.com.deathhit.core.sunflowerclonedatabasekmp.PhotoRemoteKeysEntity

interface PhotoRemoteKeysDao {
    suspend fun get(photoId: String): PhotoRemoteKeysEntity?

    suspend fun upsert(entities: List<PhotoRemoteKeysEntity>)
}