package tw.com.deathhit.data.photo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tw.com.deathhit.core.app_database.AppDatabase
import tw.com.deathhit.core.unsplash_api.UnsplashService
import tw.com.deathhit.domain.PhotoRepository
import tw.com.deathhit.domain.PlantRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PhotoRepositoryModule {
    @Provides
    @Singleton
    internal fun providePhotoRepository(
        appDatabase: AppDatabase,
        plantRepository: PlantRepository,
        unsplashService: UnsplashService
    ): PhotoRepository =
        PhotoRepositoryImp(
            appDatabase = appDatabase,
            plantRepository = plantRepository,
            unsplashService = unsplashService
        )
}