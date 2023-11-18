package tw.com.deathhit.core.app_database

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import tw.com.deathhit.core.app_database.config.generatePhotoEntities
import tw.com.deathhit.core.app_database.config.generatePhotoRemoteKeysEntity
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class PhotoRemoteKeysDaoTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    internal lateinit var appDatabase: AppDatabase

    private val photoDao get() = appDatabase.photoDao()
    private val photoRemoteKeysDao get() = appDatabase.photoRemoteKeysDao()

    @Before
    fun setup() {
        hiltRule.inject()
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