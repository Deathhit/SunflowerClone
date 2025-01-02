package tw.com.deathhit.core.sunflower_clone_database

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import tw.com.deathhit.core.sunflower_clone_database.config.buildAppDatabase
import tw.com.deathhit.core.sunflower_clone_database.config.generatePhotoEntities
import tw.com.deathhit.core.sunflower_clone_database.config.generatePhotoRemoteKeysEntity

@OptIn(ExperimentalCoroutinesApi::class)
class PhotoRemoteKeysDaoTest {
    private lateinit var sunflowerCloneDatabase: SunflowerCloneDatabase

    private val photoDao get() = sunflowerCloneDatabase.photoDao()
    private val photoRemoteKeysDao get() = sunflowerCloneDatabase.photoRemoteKeysDao()

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        sunflowerCloneDatabase = buildAppDatabase(context)
    }

    @Test
    fun getPhotoRemoteKeys() = runTest {
        //Given
        val photos = generatePhotoEntities()

        val photo = photos.random()

        val photoRemoteKeys = generatePhotoRemoteKeysEntity(photo.photoId)

        photoDao.upsert(photos)

        photoRemoteKeysDao.upsert(listOf(photoRemoteKeys))
        advanceUntilIdle()

        //When
        val queryResult = photoRemoteKeysDao.get(photoId = photoRemoteKeys.photoId)

        //Then
        assert(queryResult == photoRemoteKeys)
    }
}