package tw.com.deathhit.data.photo

import android.content.Context
import androidx.paging.testing.asSnapshot
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import tw.com.deathhit.core.sunflower_clone_database.SunflowerCloneDatabase
import tw.com.deathhit.core.sunflower_clone_database.entity.PhotoRemoteOrderEntity
import tw.com.deathhit.core.unsplash_api.protocol.model.PhotoApiEntity
import tw.com.deathhit.data.photo.config.TestUnsplashService
import tw.com.deathhit.data.photo.config.buildAppDatabase
import tw.com.deathhit.data.photo.config.generatePhotoEntities
import tw.com.deathhit.data.photo.config.generatePlantName

@OptIn(ExperimentalCoroutinesApi::class)
class PhotoRepositoryTest {
    private lateinit var sunflowerCloneDatabase: SunflowerCloneDatabase

    private val photoDao get() = sunflowerCloneDatabase.photoDao()
    private val photoRemoteOrderDao get() = sunflowerCloneDatabase.photoRemoteOrderDao()

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        sunflowerCloneDatabase = buildAppDatabase(context)
    }

    @Test
    fun getPhotoPagingDataFlow() = runTest {
        //Given
        val plantName = generatePlantName()
        val photoRepository = PhotoRepositoryImp(
            sunflowerCloneDatabase = sunflowerCloneDatabase,
            unsplashService = object : TestUnsplashService() {
                override suspend fun searchPhotos(
                    page: Int,
                    perPage: Int,
                    query: String
                ): List<PhotoApiEntity> = emptyList()
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