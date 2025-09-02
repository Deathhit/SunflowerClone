package tw.com.deathhit.app.internal.sunflower_clone_config.di.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tw.com.deathhit.core.sunflower_clone.app_database.SunflowerCloneDatabase
import tw.com.deathhit.data.sunflower_clone.garden_planting.GardenPlantingRepositoryImp
import tw.com.deathhit.domain.sunflower_clone.GardenPlantingRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GardenPlantingRepositoryModule {
    @Provides
    @Singleton
    internal fun provideGardenPlantingRepository(sunflowerCloneDatabase: SunflowerCloneDatabase): GardenPlantingRepository =
        GardenPlantingRepositoryImp(sunflowerCloneDatabase = sunflowerCloneDatabase)
}