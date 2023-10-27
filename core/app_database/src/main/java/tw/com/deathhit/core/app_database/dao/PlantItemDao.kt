package tw.com.deathhit.core.app_database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import tw.com.deathhit.core.app_database.view.PlantItemView

@Dao
interface PlantItemDao {
    @Query("SELECT * FROM PlantItemView")
    fun getEntitiesPagingSource(): PagingSource<Int, PlantItemView>
}