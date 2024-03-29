package tw.com.deathhit.config.sunflower_clone.di.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tw.com.deathhit.core.app_database.AppDatabase
import tw.com.deathhit.core.unsplash_api.UnsplashService
import tw.com.deathhit.data.photo.PhotoRepositoryImp
import tw.com.deathhit.domain.PhotoRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PhotoRepositoryModule {
    @Provides
    @Singleton
    internal fun providePhotoRepository(
        appDatabase: AppDatabase,
        unsplashService: UnsplashService
    ): PhotoRepository =
        PhotoRepositoryImp(
            appDatabase = appDatabase,
            unsplashService = unsplashService
        )
}