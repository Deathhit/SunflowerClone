package tw.com.deathhit.core.unsplash_api

import tw.com.deathhit.core.unsplash_api.model.PhotoDto
import tw.com.deathhit.core.unsplash_api.protocol.UnsplashRetrofitService

internal class UnsplashServiceImp(
    private val unsplashAppName: String,
    private val unsplashRetrofitService: UnsplashRetrofitService
) : UnsplashService {
    override fun getAttributionUrl(authorId: String): String =
        "https://unsplash.com/$authorId?utm_source=$unsplashAppName&utm_medium=referral"

    override suspend fun searchPhotos(
        page: Int,
        perPage: Int,
        query: String
    ): List<PhotoDto> = unsplashRetrofitService.searchPhotos(
        page = page,
        perPage = perPage,
        query = query
    ).results.map {
        PhotoDto(
            authorName = it.user.name,
            authorId = it.user.username,
            photoId = it.id,
            url = it.urls.small
        )
    }
}