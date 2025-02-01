package tw.com.deathhit.core.sunflower_clone_database_kmp.dao

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import tw.com.deathhit.core.sunflowerclonedatabasekmp.PlantItemView

interface PlantItemDao {
    fun getEntityFlow(plantId: String): Flow<PlantItemView?>

    fun getEntitiesPagingSource(): PagingSource<Int, PlantItemView>
}