package tw.com.deathhit.core.app_database.config

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import tw.com.deathhit.core.app_database.AppDatabase
import tw.com.deathhit.core.app_database.AppDatabaseModule
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppDatabaseModule::class]
)
object TestAppDatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
}