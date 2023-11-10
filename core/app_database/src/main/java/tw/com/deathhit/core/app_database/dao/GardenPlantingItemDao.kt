package tw.com.deathhit.core.app_database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import tw.com.deathhit.core.app_database.Column
import tw.com.deathhit.core.app_database.view.GardenPlantingItemView

@Dao
interface GardenPlantingItemDao {
    @Query("SELECT * FROM GardenPlantingItemView ORDER BY ${Column.PLANT_DATE} DESC")
    fun getEntitiesPagingSource(): PagingSource<Int, GardenPlantingItemView>
}