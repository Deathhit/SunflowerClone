package tw.com.deathhit.core.app_database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import tw.com.deathhit.core.app_database.Column
import tw.com.deathhit.core.app_database.entity.PhotoEntity

@Dao
interface PhotoDao {
    @Query("DELETE FROM PhotoEntity WHERE :plantId = ${Column.PLANT_ID}")
    suspend fun deleteByPlantId(plantId: String)

    @Query("SELECT * FROM PhotoEntity WHERE :plantId = ${Column.PLANT_ID}")
    fun getEntitiesPagingSource(plantId: String): PagingSource<Int, PhotoEntity>

    @Upsert
    suspend fun upsert(entities: List<PhotoEntity>)
}