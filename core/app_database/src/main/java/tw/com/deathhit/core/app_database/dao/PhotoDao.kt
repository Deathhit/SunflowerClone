package tw.com.deathhit.core.app_database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import tw.com.deathhit.core.app_database.Column
import tw.com.deathhit.core.app_database.entity.PhotoEntity

@Dao
interface PhotoDao {
    @Query("DELETE FROM PhotoEntity WHERE :plantName = ${Column.PLANT_NAME}")
    suspend fun clearByPlantName(plantName: String)

    @Upsert
    suspend fun upsert(entities: List<PhotoEntity>)
}