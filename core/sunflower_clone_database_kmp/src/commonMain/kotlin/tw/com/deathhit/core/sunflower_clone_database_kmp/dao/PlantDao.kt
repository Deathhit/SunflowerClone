package tw.com.deathhit.core.sunflower_clone_database_kmp.dao

import tw.com.deathhit.core.sunflowerclonedatabasekmp.PlantEntity

interface PlantDao {
    suspend fun upsert(entities: List<PlantEntity>)
}