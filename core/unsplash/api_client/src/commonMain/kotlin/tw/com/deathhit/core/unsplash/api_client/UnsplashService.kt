package tw.com.deathhit.core.unsplash.api_client

import tw.com.deathhit.core.unsplash.api_client.protocol.model.PhotoApiEntity

interface UnsplashService {
    fun getAttributionUrl(userName: String): String
    suspend fun searchPhotos(
        page: Int,
        perPage: Int,
        query: String
    ): List<PhotoApiEntity>
}