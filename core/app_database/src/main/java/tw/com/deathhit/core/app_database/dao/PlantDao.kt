package tw.com.deathhit.core.app_database.dao

import androidx.room.Dao
import androidx.room.Upsert
import tw.com.deathhit.core.app_database.entity.PlantEntity

@Dao
interface PlantDao {
    @Upsert
    suspend fun upsert(entities: List<PlantEntity>)
}