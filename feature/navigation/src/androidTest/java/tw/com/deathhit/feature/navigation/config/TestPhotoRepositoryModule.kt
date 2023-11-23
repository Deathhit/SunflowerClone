package tw.com.deathhit.feature.navigation.config

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tw.com.deathhit.domain.PhotoRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestPhotoRepositoryModule {
    @Provides
    @Singleton
    internal fun providePhotoRepository(): PhotoRepository = TestPhotoRepository()
}