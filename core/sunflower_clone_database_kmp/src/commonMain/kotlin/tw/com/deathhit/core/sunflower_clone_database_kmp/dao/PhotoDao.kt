package tw.com.deathhit.core.sunflower_clone_database_kmp.dao

import tw.com.deathhit.core.sunflowerclonedatabasekmp.PhotoEntity

interface PhotoDao {
    suspend fun clearByPlantName(plantName: String)

    suspend fun upsert(entities: List<PhotoEntity>)
}