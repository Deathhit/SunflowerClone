package tw.com.deathhit.config.sunflower_clone.di.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tw.com.deathhit.core.sunflower_clone_database.SunflowerCloneDatabase
import tw.com.deathhit.data.plant.PlantRepositoryImp
import tw.com.deathhit.domain.PlantRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlantRepositoryModule {
    @Provides
    @Singleton
    internal fun providePlantRepository(sunflowerCloneDatabase: SunflowerCloneDatabase): PlantRepository =
        PlantRepositoryImp(sunflowerCloneDatabase = sunflowerCloneDatabase)
}