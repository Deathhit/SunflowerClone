package tw.com.deathhit.core.unsplash_api

import tw.com.deathhit.core.unsplash_api.protocol.UnsplashRetrofitService
import tw.com.deathhit.core.unsplash_api.protocol.model.PhotoApiEntity

internal class UnsplashServiceImp(
    private val unsplashAppName: String,
    private val unsplashRetrofitService: UnsplashRetrofitService
) : UnsplashService {
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
}