package tw.com.deathhit.core.unsplash_api.protocol

import retrofit2.http.GET
import retrofit2.http.Query
import tw.com.deathhit.core.unsplash_api.protocol.response.SearchPhotosResponse

internal interface UnsplashRetrofitService {
    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("query") query: String
    ): SearchPhotosResponse
}