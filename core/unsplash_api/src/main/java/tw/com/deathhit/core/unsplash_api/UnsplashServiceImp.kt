package tw.com.deathhit.core.unsplash_api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tw.com.deathhit.core.unsplash_api.protocol.UnsplashAuthenticator
import tw.com.deathhit.core.unsplash_api.protocol.UnsplashRetrofitService
import tw.com.deathhit.core.unsplash_api.protocol.model.PhotoApiEntity

class UnsplashServiceImp(
    accessKey: String,
    serverUrl: String,
    private val unsplashAppName: String
) : UnsplashService {
    private val unsplashRetrofitService: UnsplashRetrofitService =
        createUnsplashRetrofitService(accessKey = accessKey, serverUrl = serverUrl)

    override fun getAttributionUrl(userName: String): String =
        "https://unsplash.com/$userName?utm_source=$unsplashAppName&utm_medium=referral"

    override suspend fun searchPhotos(
        page: Int,
        perPage: Int,
        query: String
    ): List<PhotoApiEntity> = unsplashRetrofitService.searchPhotos(
        page = page,
        perPage = perPage,
        query = query
    ).results

    private fun createOkHttpClient(accessKey: String) = OkHttpClient.Builder()
        .authenticator(UnsplashAuthenticator { accessKey })
        .build()

    private fun createRetrofit(accessKey: String, serverUrl: String) =
        Retrofit.Builder().client(createOkHttpClient(accessKey = accessKey))
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(serverUrl)
            .build()

    private fun createUnsplashRetrofitService(accessKey: String, serverUrl: String) =
        createRetrofit(accessKey = accessKey, serverUrl = serverUrl).create(
            UnsplashRetrofitService::class.java
        )
}