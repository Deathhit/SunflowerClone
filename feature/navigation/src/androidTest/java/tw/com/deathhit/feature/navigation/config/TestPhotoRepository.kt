package tw.com.deathhit.feature.navigation.config

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tw.com.deathhit.domain.PhotoRepository
import tw.com.deathhit.domain.model.PhotoDO

class TestPhotoRepository : PhotoRepository {
    override fun getPhotoPagingDataFlow(plantName: String): Flow<PagingData<PhotoDO>> {
        throw UnsupportedOperationException()
    }
}