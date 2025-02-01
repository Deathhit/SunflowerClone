package tw.com.deathhit.core.sunflower_clone_database_kmp.dao

import tw.com.deathhit.core.sunflowerclonedatabasekmp.GardenPlantingEntity

interface GardenPlantingDao {
    suspend fun upsert(entities: List<GardenPlantingEntity>)
}