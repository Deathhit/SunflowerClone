package tw.com.deathhit.core.unsplash_api

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tw.com.deathhit.core.unsplash_api.protocol.UnsplashAuthenticator
import tw.com.deathhit.core.unsplash_api.protocol.UnsplashRetrofitService
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UnsplashApiModule {
    @Provides
    @Singleton
    @UnsplashOkHttpClient
    internal fun provideUnsplashOkHttpClient(@ApplicationContext context: Context) =
        OkHttpClient.Builder()
            .authenticator(UnsplashAuthenticator {
                context.getAccessKey()
            })
            .build()

    @Provides
    @Singleton
    @UnsplashRetrofit
    internal fun provideUnsplashRetrofit(
        @ApplicationContext context: Context,
        @UnsplashOkHttpClient client: OkHttpClient
    ) = Retrofit.Builder().client(client).addConverterFactory(GsonConverterFactory.create())
        .baseUrl(context.getServerUrl())
        .build()

    @Provides
    @Singleton
    internal fun provideUnsplashRetrofitService(@UnsplashRetrofit retrofit: Retrofit) =
        retrofit.create(UnsplashRetrofitService::class.java)

    @Provides
    @Singleton
    internal fun provideUnsplashService(
        @ApplicationContext context: Context,
        unsplashRetrofitService: UnsplashRetrofitService
    ): UnsplashService = UnsplashServiceImp(
        unsplashAppName = context.getAppName(),
        unsplashRetrofitService = unsplashRetrofitService
    )

    private fun Context.getAccessKey() =
        getMetadataString("tw.com.deathhit.core.unsplash_api.access_key")

    private fun Context.getAppName() =
        getMetadataString("tw.com.deathhit.core.unsplash_api.app_name")

    private fun Context.getServerUrl() =
        getMetadataString("tw.com.deathhit.core.unsplash_api.server_url")

    private fun Context.getMetadataString(key: String) = with(packageManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getPackageInfo(
                packageName,
                PackageManager.PackageInfoFlags.of(PackageManager.GET_META_DATA.toLong())
            )
        } else {
            getPackageInfo(
                packageName,
                PackageManager.GET_META_DATA
            )
        }
    }.applicationInfo.metaData.getString(key)!!

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    internal annotation class UnsplashOkHttpClient

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    internal annotation class UnsplashRetrofit
}