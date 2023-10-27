package tw.com.deathhit.core.app_database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import tw.com.deathhit.core.app_database.view.GardenPlantingItemView

@Dao
interface GardenPlantingItemDao {
    @Query("SELECT * FROM GardenPlantingItemView")
    fun getEntitiesPagingSource(): PagingSource<Int, GardenPlantingItemView>
}