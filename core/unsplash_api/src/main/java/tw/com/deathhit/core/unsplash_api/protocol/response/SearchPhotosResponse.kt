package tw.com.deathhit.core.unsplash_api.protocol.response

import com.google.gson.annotations.SerializedName
import tw.com.deathhit.core.unsplash_api.protocol.model.Photo

internal data class SearchPhotosResponse(
    @SerializedName("results") val results: List<Photo>,
    @SerializedName("total_pages") val totalPages: Int
)