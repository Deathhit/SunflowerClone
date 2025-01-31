package tw.com.deathhit.app.sunflower_clone_kmp_config.di.data

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
    //todo Replace with the KMP variant
    @Provides
    @Singleton
    internal fun providePlantRepository(sunflowerCloneDatabase: SunflowerCloneDatabase): PlantRepository =
        PlantRepositoryImp(sunflowerCloneDatabase = sunflowerCloneDatabase)
}