package tw.com.deathhit.core.unsplash.api_client.protocol.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoUrlsApiEntity(
    @SerialName("small") val small: String
)
