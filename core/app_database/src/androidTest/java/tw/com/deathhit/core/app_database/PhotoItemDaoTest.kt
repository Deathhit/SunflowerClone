package tw.com.deathhit.core.app_database

import android.content.Context
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import tw.com.deathhit.core.app_database.config.buildAppDatabase
import tw.com.deathhit.core.app_database.config.generatePhotoEntities
import tw.com.deathhit.core.app_database.config.generatePlantName
import tw.com.deathhit.core.app_database.entity.PhotoEntity
import tw.com.deathhit.core.app_database.entity.PhotoRemoteOrderEntity
import tw.com.deathhit.core.app_database.view.PhotoItemView

@OptIn(ExperimentalCoroutinesApi::class)
class PhotoItemDaoTest {
    private lateinit var appDatabase: AppDatabase

    private val photoDao get() = appDatabase.photoDao()
    private val photoItemDao get() = appDatabase.photoItemDao()
    private val photoRemoteOrderDao get() = appDatabase.photoRemoteOrderDao()

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        appDatabase = buildAppDatabase(context)
    }

    @Test
    fun getPhotoItemsPagingSource() = runTest {
        //Given
        val plantName = generatePlantName()

        val photos = generatePhotoEntities(plantName = plantName)
        val photoRemoteOrders = photos.mapIndexed { index, photoEntity ->
            PhotoRemoteOrderEntity(photoId = photoEntity.photoId, remoteOrder = index)
        }

        photoDao.upsert(photos)
        photoRemoteOrderDao.upsert(photoRemoteOrders)
        advanceUntilIdle()

        //When
        val photoItems =
            (TestPager(
                PagingConfig(photos.size),
                photoItemDao.getEntitiesPagingSource(plantName = plantName)
            ).refresh() as PagingSource.LoadResult.Page).data

        //Then
        photoItems.forEachIndexed { index, photoItem ->
            photoItem.assertEqualsToEntity(photos[index])
        }
    }

    private fun PhotoItemView.assertEqualsToEntity(entity: PhotoEntity) {
        assert(authorId == entity.authorId)
        assert(authorName == entity.authorName)
        assert(imageUrl == entity.imageUrl)
        assert(photoId == entity.photoId)
        assert(plantName == entity.plantName)
    }
}