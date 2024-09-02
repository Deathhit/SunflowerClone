package tw.com.deathhit.core.unsplash_api

import tw.com.deathhit.core.unsplash_api.protocol.model.PhotoApiEntity

interface UnsplashService {
    fun getAttributionUrl(userName: String): String
    suspend fun searchPhotos(
        page: Int,
        perPage: Int,
        query: String
    ): List<PhotoApiEntity>
}