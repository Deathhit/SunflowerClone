package tw.com.deathhit.core.app_database

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import tw.com.deathhit.core.app_database.config.buildAppDatabase
import tw.com.deathhit.core.app_database.config.generatePhotoEntities
import tw.com.deathhit.core.app_database.config.generatePhotoRemoteKeysEntity

@OptIn(ExperimentalCoroutinesApi::class)
class PhotoRemoteKeysDaoTest {
    private lateinit var appDatabase: AppDatabase

    private val photoDao get() = appDatabase.photoDao()
    private val photoRemoteKeysDao get() = appDatabase.photoRemoteKeysDao()

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        appDatabase = buildAppDatabase(context)
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