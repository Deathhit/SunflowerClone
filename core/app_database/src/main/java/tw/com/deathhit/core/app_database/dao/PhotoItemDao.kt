package tw.com.deathhit.core.app_database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import tw.com.deathhit.core.app_database.Column
import tw.com.deathhit.core.app_database.view.PhotoItemView

@Dao
interface PhotoItemDao {
    @Query("SELECT * FROM PhotoItemView WHERE :plantId = ${Column.PLANT_ID} ORDER BY ${Column.REMOTE_ORDER} ASC")
    fun getEntitiesPagingSource(plantId: String): PagingSource<Int, PhotoItemView>
}