package tw.com.deathhit.feature.gallery.config

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.testing.asPagingSourceFactory
import kotlinx.coroutines.flow.Flow
import tw.com.deathhit.domain.PhotoRepository
import tw.com.deathhit.domain.model.PhotoDO

class TestPhotoRepository : PhotoRepository {
    private val mutableMap: MutableMap<String, List<PhotoDO>> = mutableMapOf()

    override fun getPhotoPagingDataFlow(plantName: String): Flow<PagingData<PhotoDO>> = Pager(
        config = PagingConfig(pageSize = 25),
        pagingSourceFactory = mutableMap.getOrPut(
            plantName,
            defaultValue = { generatePhotoDOs(plantName) }).asPagingSourceFactory()
    ).flow
}