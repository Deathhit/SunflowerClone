package tw.com.deathhit.core.unsplash_api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tw.com.deathhit.core.unsplash_api.model.PhotoDto
import tw.com.deathhit.core.unsplash_api.protocol.UnsplashAuthenticator
import tw.com.deathhit.core.unsplash_api.protocol.UnsplashRetrofitService

interface UnsplashService {
    fun getAttributionUrl(authorId: String): String
    suspend fun searchPhotos(
        page: Int,
        perPage: Int,
        query: String
    ): List<PhotoDto>

    companion object {
        fun createUnsplashService(
            accessKey: String,
            appName: String,
            serverUrl: String
        ): UnsplashService = UnsplashServiceImp(
            unsplashAppName = appName,
            unsplashRetrofitService = createUnsplashRetrofitService(
                accessKey = accessKey,
                serverUrl = serverUrl
            )
        )

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
}