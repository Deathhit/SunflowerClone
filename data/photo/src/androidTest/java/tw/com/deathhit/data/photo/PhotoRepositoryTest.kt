package tw.com.deathhit.data.photo

import androidx.paging.testing.asSnapshot
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import tw.com.deathhit.core.app_database.AppDatabase
import tw.com.deathhit.core.app_database.entity.PhotoRemoteOrderEntity
import tw.com.deathhit.core.unsplash_api.model.PhotoDto
import tw.com.deathhit.data.photo.config.TestUnsplashService
import tw.com.deathhit.data.photo.config.generatePhotoEntities
import tw.com.deathhit.data.photo.config.generatePlantName
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class PhotoRepositoryTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    internal lateinit var appDatabase: AppDatabase

    private val photoDao get() = appDatabase.photoDao()
    private val photoRemoteOrderDao get() = appDatabase.photoRemoteOrderDao()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun getPhotoPagingDataFlow() = runTest {
        //Given
        val plantName = generatePlantName()
        val photoRepository = PhotoRepositoryImp(
            appDatabase = appDatabase,
            unsplashService = object : TestUnsplashService() {
                override suspend fun searchPhotos(
                    page: Int,
                    perPage: Int,
                    query: String
                ): List<PhotoDto> = emptyList()
            })

        val photoEntities = generatePhotoEntities(plantName = plantName)
        val photoRemoteOrderEntities = photoEntities.mapIndexed { index, photoEntity ->
            PhotoRemoteOrderEntity(photoId = photoEntity.photoId, remoteOrder = index)
        }

        photoDao.upsert(photoEntities)
        photoRemoteOrderDao.upsert(photoRemoteOrderEntities)
        advanceUntilIdle()

        //When
        val photos = photoRepository.getPhotoPagingDataFlow(plantName = plantName)
        val photosSnapshot = photos.asSnapshot {
            scrollTo(photoEntities.size)
        }

        //Then
        photosSnapshot.forEachIndexed { index, photo ->
            val photoEntity = photoEntities[index]

            assert(photo.authorId == photoEntity.authorId)
            assert(photo.authorName == photoEntity.authorName)
            assert(photo.imageUrl == photoEntity.imageUrl)
            assert(photo.photoId == photoEntity.photoId)
            assert(photo.plantName == photoEntity.plantName)
        }
    }
}