package tw.com.deathhit.core.app_database

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import tw.com.deathhit.core.app_database.config.generatePhotoEntities
import tw.com.deathhit.core.app_database.config.generatePlantName
import tw.com.deathhit.core.app_database.entity.PhotoEntity
import tw.com.deathhit.core.app_database.entity.PhotoRemoteOrderEntity
import tw.com.deathhit.core.app_database.view.PhotoItemView
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class PhotoItemDaoTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    internal lateinit var appDatabase: AppDatabase

    private val photoDao get() = appDatabase.photoDao()
    private val photoItemDao get() = appDatabase.photoItemDao()
    private val photoRemoteOrderDao get() = appDatabase.photoRemoteOrderDao()

    @Before
    fun setup() {
        hiltRule.inject()
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