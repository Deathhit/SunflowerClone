package tw.com.deathhit.sunflower_clone.di.core

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tw.com.deathhit.core.app_database.AppDatabase.Companion.buildAppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {
    @Provides
    @Singleton
    internal fun provideAppDatabase(@ApplicationContext context: Context) = context.buildAppDatabase()
}