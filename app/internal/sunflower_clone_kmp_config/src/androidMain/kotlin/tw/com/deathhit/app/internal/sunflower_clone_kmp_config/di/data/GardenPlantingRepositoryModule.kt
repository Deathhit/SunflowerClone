package tw.com.deathhit.app.internal.sunflower_clone_kmp_config.di.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tw.com.deathhit.core.sunflower_clone_database_kmp.SunflowerCloneDatabase
import tw.com.deathhit.data.garden_planting_kmp.GardenPlantingRepositoryImp
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