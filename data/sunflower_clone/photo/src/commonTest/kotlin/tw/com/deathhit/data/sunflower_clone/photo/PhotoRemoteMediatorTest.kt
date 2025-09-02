package tw.com.deathhit.data.sunflower_clone.photo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import kotlinx.coroutines.test.runTest
import tw.com.deathhit.core.sunflower_clone.app_database.SunflowerCloneDatabase
import tw.com.deathhit.core.sunflower_clone.app_database.view.PhotoItemView
import tw.com.deathhit.core.unsplash.api_client.protocol.model.PhotoApiEntity
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@OptIn(ExperimentalPagingApi::class)
abstract class PhotoRemoteMediatorTest {
    private lateinit var sunflowerCloneDatabase: SunflowerCloneDatabase

    @BeforeTest
    fun setup() {
        sunflowerCloneDatabase = createAppDatabase()
    }

    @Test
    fun refreshLoad_errorOccurs_returnsErrorResult() = runTest {
        //Given
        val pagingState = PagingState<Int, PhotoItemView>(
            listOf(),
            null,
            PagingConfig(1),
            0
        )
        val unsplashService = object : TestUnsplashService() {
            override suspend fun searchPhotos(
                page: Int,
                perPage: Int,
                query: String
            ): List<PhotoApiEntity> {
                throw RuntimeException("Test")
            }
        }

        val remoteMediator = PhotoRemoteMediator(
            plantName = "plantName",
            sunflowerCloneDatabase = sunflowerCloneDatabase,
            unsplashService = unsplashService
        )

        //When
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)

        //Then
        assertTrue(result is RemoteMediator.MediatorResult.Error)
    }

    @Test
    fun refreshLoad_moreDataIsPresent_returnsSuccessResult() = runTest {
        //Given
        val pageSize = 3

        val pagingState = PagingState<Int, PhotoItemView>(
            listOf(),
            null,
            PagingConfig(pageSize),
            0
        )
        val unsplashService = object : TestUnsplashService() {
            override suspend fun searchPhotos(
                page: Int,
                perPage: Int,
                query: String
            ): List<PhotoApiEntity> = generatePhotoApiEntities(from = pageSize + 1, until = pageSize + 2)
        }

        val remoteMediator = PhotoRemoteMediator(
            plantName = "plantName",
            sunflowerCloneDatabase = sunflowerCloneDatabase,
            unsplashService = unsplashService
        )

        //When
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)

        //Then
        assertTrue(result is RemoteMediator.MediatorResult.Success)
        assertFalse(result.endOfPaginationReached)
    }

    @Test
    fun refreshLoad_noMoreData_returnSuccessAndEndOfPagination() = runTest {
        //Given
        val pageSize = 3

        val pagingState = PagingState<Int, PhotoItemView>(
            listOf(),
            null,
            PagingConfig(pageSize),
            0
        )
        val unsplashService = object : TestUnsplashService() {
            override suspend fun searchPhotos(
                page: Int,
                perPage: Int,
                query: String
            ): List<PhotoApiEntity> = emptyList()
        }

        val remoteMediator = PhotoRemoteMediator(
            plantName = "plantName",
            sunflowerCloneDatabase = sunflowerCloneDatabase,
            unsplashService = unsplashService
        )

        //When
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)

        //Then
        assertTrue(result is RemoteMediator.MediatorResult.Success)
        assertTrue(result.endOfPaginationReached)
    }

    protected abstract fun createAppDatabase(): SunflowerCloneDatabase
}