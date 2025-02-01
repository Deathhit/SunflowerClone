package tw.com.deathhit.app.internal.sunflower_clone_kmp_config.di.core

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tw.com.deathhit.core.sunflower_clone_database_kmp.DriverFactory
import tw.com.deathhit.core.sunflower_clone_database_kmp.createDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SunflowerCloneDatabaseModule {
    @Provides
    @Singleton
    internal fun provideAppDatabase(@ApplicationContext context: Context) = createDatabase(
        DriverFactory(
            context = context,
            databaseName = "sunflower_clone_database_3f86b669755d4f27a1613b339bd87def"
        )
    )
}