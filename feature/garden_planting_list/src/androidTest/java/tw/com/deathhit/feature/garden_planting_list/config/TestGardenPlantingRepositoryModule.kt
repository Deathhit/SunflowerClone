package tw.com.deathhit.feature.garden_planting_list.config

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tw.com.deathhit.domain.GardenPlantingRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestGardenPlantingRepositoryModule {
    @Provides
    @Singleton
    internal fun provideGardenPlantingRepository(): GardenPlantingRepository =
        TestGardenPlantingRepository()
}