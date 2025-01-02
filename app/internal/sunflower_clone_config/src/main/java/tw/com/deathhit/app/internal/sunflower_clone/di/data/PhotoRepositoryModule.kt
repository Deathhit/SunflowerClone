package tw.com.deathhit.app.internal.sunflower_clone.di.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tw.com.deathhit.core.sunflower_clone_database.SunflowerCloneDatabase
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
        sunflowerCloneDatabase: SunflowerCloneDatabase,
        unsplashService: UnsplashService
    ): PhotoRepository =
        PhotoRepositoryImp(
            sunflowerCloneDatabase = sunflowerCloneDatabase,
            unsplashService = unsplashService
        )
}