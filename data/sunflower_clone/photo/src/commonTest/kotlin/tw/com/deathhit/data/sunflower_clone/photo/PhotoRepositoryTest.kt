package tw.com.deathhit.data.sunflower_clone.photo

import androidx.paging.testing.asSnapshot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import tw.com.deathhit.core.sunflower_clone.app_database.SunflowerCloneDatabase
import tw.com.deathhit.core.unsplash.api_client.protocol.model.PhotoApiEntity
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
abstract class PhotoRepositoryTest {
    private lateinit var sunflowerCloneDatabase: SunflowerCloneDatabase

    private val photoDao get() = sunflowerCloneDatabase.photoDao()

    @BeforeTest
    fun setup() {
        sunflowerCloneDatabase = createAppDatabase()
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

        photoDao.upsertPhotoPage(
            entities = photoEntities,
            page = 1,
            pageSize = photoEntities.size
        )
        advanceUntilIdle()

        //When
        val photos = photoRepository.getPhotoPagingDataFlow(plantName = plantName)
        val photosSnapshot = photos.asSnapshot {
            scrollTo(photoEntities.size)
        }

        //Then
        photosSnapshot.forEachIndexed { index, photo ->
            val photoEntity = photoEntities[index]

            assertTrue(photo.authorId == photoEntity.authorId)
            assertTrue(photo.authorName == photoEntity.authorName)
            assertTrue(photo.imageUrl == photoEntity.imageUrl)
            assertTrue(photo.photoId == photoEntity.photoId)
            assertTrue(photo.plantName == photoEntity.plantName)
        }
    }

    protected abstract fun createAppDatabase(): SunflowerCloneDatabase
}