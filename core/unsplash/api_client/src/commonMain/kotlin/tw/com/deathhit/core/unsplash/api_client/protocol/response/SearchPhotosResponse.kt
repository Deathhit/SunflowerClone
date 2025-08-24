package tw.com.deathhit.core.unsplash.api_client.protocol.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tw.com.deathhit.core.unsplash.api_client.protocol.model.PhotoApiEntity

@Serializable
internal data class SearchPhotosResponse(
    @SerialName("results") val results: List<PhotoApiEntity>,
    @SerialName("total_pages") val totalPages: Int
)