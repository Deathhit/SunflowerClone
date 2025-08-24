package tw.com.deathhit.core.sunflower_clone.app_database.dao

import androidx.room.Dao
import androidx.room.Upsert
import tw.com.deathhit.core.sunflower_clone.app_database.entity.PlantEntity

@Dao
interface PlantDao {
    @Upsert
    suspend fun upsert(entities: List<PlantEntity>)
}