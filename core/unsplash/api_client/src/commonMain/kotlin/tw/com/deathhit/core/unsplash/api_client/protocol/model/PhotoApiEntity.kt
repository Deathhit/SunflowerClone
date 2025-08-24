package tw.com.deathhit.core.unsplash.api_client.protocol.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoApiEntity(
    @SerialName("id") val id: String,
    @SerialName("urls") val urls: PhotoUrlsApiEntity,
    @SerialName("user") val user: UserApiEntity
)
