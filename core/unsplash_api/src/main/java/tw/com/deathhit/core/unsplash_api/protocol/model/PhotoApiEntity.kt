package tw.com.deathhit.core.unsplash_api.protocol.model

import com.google.gson.annotations.SerializedName

data class PhotoApiEntity(
    @SerializedName("id") val id: String,
    @SerializedName("urls") val urls: PhotoUrlsApiEntity,
    @SerializedName("user") val user: UserApiEntity
)
