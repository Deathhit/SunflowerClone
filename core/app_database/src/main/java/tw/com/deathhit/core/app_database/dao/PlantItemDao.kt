package tw.com.deathhit.core.app_database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tw.com.deathhit.core.app_database.Column
import tw.com.deathhit.core.app_database.view.PlantItemView

@Dao
interface PlantItemDao {
    @Query("SELECT * FROM PlantItemView WHERE :plantId = ${Column.PLANT_ID}")
    fun getEntityFlow(plantId: String): Flow<PlantItemView?>

    @Query("SELECT * FROM PlantItemView ORDER BY ${Column.PLANT_NAME} ASC")
    fun getEntitiesPagingSource(): PagingSource<Int, PlantItemView>
}