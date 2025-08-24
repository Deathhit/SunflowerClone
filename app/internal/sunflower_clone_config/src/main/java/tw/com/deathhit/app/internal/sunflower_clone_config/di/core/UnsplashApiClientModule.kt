package tw.com.deathhit.app.internal.sunflower_clone_config.di.core

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tw.com.deathhit.app.internal.sunflower_clone_config.getUnsplashAccessKey
import tw.com.deathhit.app.internal.sunflower_clone_config.getUnsplashAppName
import tw.com.deathhit.app.internal.sunflower_clone_config.getUnsplashServerUrl
import tw.com.deathhit.core.unsplash.api_client.UnsplashService
import tw.com.deathhit.core.unsplash.api_client.UnsplashServiceImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UnsplashApiClientModule {
    @Provides
    @Singleton
    internal fun provideUnsplashService(@ApplicationContext context: Context): UnsplashService =
        UnsplashServiceImp(
            accessKey = context.getUnsplashAccessKey(),
            serverUrl = context.getUnsplashServerUrl(),
            unsplashAppName = context.getUnsplashAppName()
        )
}