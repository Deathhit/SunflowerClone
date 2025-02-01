package tw.com.deathhit.core.sunflower_clone_database_kmp.dao

import androidx.paging.PagingSource
import tw.com.deathhit.core.sunflowerclonedatabasekmp.GardenPlantingItemView

interface GardenPlantingItemDao {
    fun getEntitiesPagingSource(): PagingSource<Int, GardenPlantingItemView>
}