package tw.com.deathhit.core.sunflower_clone_database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import tw.com.deathhit.core.sunflower_clone_database.Column
import tw.com.deathhit.core.sunflower_clone_database.view.PhotoItemView

@Dao
interface PhotoItemDao {
    @Query("SELECT * FROM PhotoItemView WHERE :plantName = ${Column.PLANT_NAME} ORDER BY ${Column.REMOTE_ORDER} ASC")
    fun getEntitiesPagingSource(plantName: String): PagingSource<Int, PhotoItemView>
}