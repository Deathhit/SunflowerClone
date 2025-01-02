package tw.com.deathhit.core.sunflower_clone_database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import tw.com.deathhit.core.sunflower_clone_database.Column
import tw.com.deathhit.core.sunflower_clone_database.view.GardenPlantingItemView

@Dao
interface GardenPlantingItemDao {
    @Query("SELECT * FROM GardenPlantingItemView ORDER BY ${Column.PLANT_DATE} DESC")
    fun getEntitiesPagingSource(): PagingSource<Int, GardenPlantingItemView>
}