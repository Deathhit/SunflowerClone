package tw.com.deathhit.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tw.com.deathhit.domain.model.PhotoDO

interface PhotoRepository {
    fun getPhotoPagingDataFlow(plantName: String): Flow<PagingData<PhotoDO>>
}