package tw.com.deathhit.core.unsplash_api.protocol.model

import com.google.gson.annotations.SerializedName

internal data class Photo(
    @SerializedName("id") val id: String,
    @SerializedName("urls") val urls: PhotoUrls,
    @SerializedName("user") val user: User
)
