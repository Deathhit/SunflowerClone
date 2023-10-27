package tw.com.deathhit.core.unsplash_api

import tw.com.deathhit.core.unsplash_api.model.Photo
import tw.com.deathhit.core.unsplash_api.protocol.UnsplashRetrofitService

internal class UnsplashServiceImp(
    private val unsplashAppName: String,
    private val unsplashRetrofitService: UnsplashRetrofitService
) : UnsplashService {
    override fun getAttributionUrl(username: String): String =
        "https://unsplash.com/$username?utm_source=$unsplashAppName&utm_medium=referral"

    override suspend fun searchPhotos(
        page: Int,
        perPage: Int,
        query: String
    ): List<Photo> = unsplashRetrofitService.searchPhotos(
        page = page,
        perPage = perPage,
        query = query
    ).results.map {
        Photo(
            authorName = it.user.name,
            authorUserName = it.user.username,
            photoId = it.id,
            url = it.urls.small
        )
    }
}