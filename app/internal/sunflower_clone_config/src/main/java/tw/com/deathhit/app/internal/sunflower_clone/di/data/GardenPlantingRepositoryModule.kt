package tw.com.deathhit.app.internal.sunflower_clone.di.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tw.com.deathhit.core.sunflower_clone_database.SunflowerCloneDatabase
import tw.com.deathhit.data.garden_planting.GardenPlantingRepositoryImp
import tw.com.deathhit.domain.GardenPlantingRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GardenPlantingRepositoryModule {
    @Provides
    @Singleton
    internal fun provideGardenPlantingRepository(sunflowerCloneDatabase: SunflowerCloneDatabase): GardenPlantingRepository =
        GardenPlantingRepositoryImp(sunflowerCloneDatabase = sunflowerCloneDatabase)
}