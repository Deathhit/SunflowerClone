package tw.com.deathhit.data.garden_planting

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tw.com.deathhit.core.app_database.AppDatabase
import tw.com.deathhit.domain.GardenPlantingRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GardenPlantingRepositoryModule {
    @Provides
    @Singleton
    internal fun provideGardenPlantingRepository(appDatabase: AppDatabase): GardenPlantingRepository =
        GardenPlantingRepositoryImp(appDatabase = appDatabase)
}