package tw.com.deathhit.core.sunflower_clone_database_kmp.dao

import tw.com.deathhit.core.sunflowerclonedatabasekmp.PhotoRemoteOrderEntity

interface PhotoRemoteOrderDao {
    suspend fun upsert(entities: List<PhotoRemoteOrderEntity>)
}