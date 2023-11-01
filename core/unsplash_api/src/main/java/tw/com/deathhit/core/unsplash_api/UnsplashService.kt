package tw.com.deathhit.core.unsplash_api

import tw.com.deathhit.core.unsplash_api.model.Photo

interface UnsplashService {
    fun getAttributionUrl(authorId: String): String
    suspend fun searchPhotos(
        page: Int,
        perPage: Int,
        query: String
    ): List<Photo>
}