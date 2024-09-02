package tw.com.deathhit.core.unsplash_api.protocol.response

import com.google.gson.annotations.SerializedName
import tw.com.deathhit.core.unsplash_api.protocol.model.PhotoApiEntity

internal data class SearchPhotosResponse(
    @SerializedName("results") val results: List<PhotoApiEntity>,
    @SerializedName("total_pages") val totalPages: Int
)