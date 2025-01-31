package tw.com.deathhit.app.sunflower_clone_kmp_config.di.core

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tw.com.deathhit.app.sunflower_clone_kmp_config.getUnsplashAccessKey
import tw.com.deathhit.app.sunflower_clone_kmp_config.getUnsplashAppName
import tw.com.deathhit.app.sunflower_clone_kmp_config.getUnsplashServerUrl
import tw.com.deathhit.core.unsplash_api.UnsplashService
import tw.com.deathhit.core.unsplash_api.UnsplashServiceImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UnsplashApiModule {
    //todo Replace with the KMP variant
    @Provides
    @Singleton
    internal fun provideUnsplashService(@ApplicationContext context: Context): UnsplashService =
        UnsplashServiceImp(
            accessKey = context.getUnsplashAccessKey(),
            serverUrl = context.getUnsplashServerUrl(),
            unsplashAppName = context.getUnsplashAppName()
        )
}