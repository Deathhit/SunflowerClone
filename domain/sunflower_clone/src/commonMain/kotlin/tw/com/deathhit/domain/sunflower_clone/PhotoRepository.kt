package tw.com.deathhit.domain.sunflower_clone

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tw.com.deathhit.domain.sunflower_clone.model.PhotoDO

interface PhotoRepository {
    fun getPhotoPagingDataFlow(plantName: String): Flow<PagingData<PhotoDO>>
}