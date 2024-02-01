package tw.com.deathhit.sunflower_clone.di.core

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tw.com.deathhit.core.unsplash_api.UnsplashService.Companion.createUnsplashService
import tw.com.deathhit.sunflower_clone.navigation.getUnsplashAccessKey
import tw.com.deathhit.sunflower_clone.navigation.getUnsplashAppName
import tw.com.deathhit.sunflower_clone.navigation.getUnsplashServerUrl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UnsplashApiModule {
    @Provides
    @Singleton
    internal fun provideUnsplashService(@ApplicationContext context: Context) =
        createUnsplashService(
            accessKey = context.getUnsplashAccessKey(),
            appName = context.getUnsplashAppName(),
            serverUrl = context.getUnsplashServerUrl()
        )
}