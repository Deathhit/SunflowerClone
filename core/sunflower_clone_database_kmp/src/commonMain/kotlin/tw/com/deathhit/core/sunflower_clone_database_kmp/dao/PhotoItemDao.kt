package tw.com.deathhit.core.sunflower_clone_database_kmp.dao

import androidx.paging.PagingSource
import tw.com.deathhit.core.sunflowerclonedatabasekmp.PhotoItemView

interface PhotoItemDao {
    fun getEntitiesPagingSource(plantName: String): PagingSource<Int, PhotoItemView>
}