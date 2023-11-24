package tw.com.deathhit.data.photo

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import tw.com.deathhit.core.app_database.AppDatabase
import tw.com.deathhit.core.app_database.view.PhotoItemView
import tw.com.deathhit.core.unsplash_api.model.PhotoDto
import tw.com.deathhit.data.photo.config.TestUnsplashService
import tw.com.deathhit.data.photo.config.buildAppDatabase
import tw.com.deathhit.data.photo.config.generatePhotoDtoList

@OptIn(ExperimentalPagingApi::class)
class PhotoRemoteMediatorTest {
    private lateinit var appDatabase: AppDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        appDatabase = buildAppDatabase(context)
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
            ): List<PhotoDto> {
                throw RuntimeException("Test")
            }
        }

        val remoteMediator = PhotoRemoteMediator(
            appDatabase = appDatabase,
            plantName = "plantName",
            unsplashService = unsplashService
        )

        //When
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)

        //Then
        Assert.assertTrue(result is RemoteMediator.MediatorResult.Error)
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
            ): List<PhotoDto> = generatePhotoDtoList(from = pageSize + 1, until = pageSize + 2)
        }

        val remoteMediator = PhotoRemoteMediator(
            appDatabase = appDatabase,
            plantName = "plantName",
            unsplashService = unsplashService
        )

        //When
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)

        //Then
        Assert.assertTrue(result is RemoteMediator.MediatorResult.Success)
        Assert.assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
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
            ): List<PhotoDto> = emptyList()
        }

        val remoteMediator = PhotoRemoteMediator(
            appDatabase = appDatabase,
            plantName = "plantName",
            unsplashService = unsplashService
        )

        //When
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)

        //Then
        Assert.assertTrue(result is RemoteMediator.MediatorResult.Success)
        Assert.assertTrue((result is RemoteMediator.MediatorResult.Success) && result.endOfPaginationReached)
    }
}