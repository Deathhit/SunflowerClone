package tw.com.deathhit.config.sunflower_clone.di.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tw.com.deathhit.core.app_database.AppDatabase
import tw.com.deathhit.data.plant.PlantRepositoryImp
import tw.com.deathhit.domain.PlantRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlantRepositoryModule {
    @Provides
    @Singleton
    internal fun providePlantRepository(appDatabase: AppDatabase): PlantRepository =
        PlantRepositoryImp(appDatabase = appDatabase)
}