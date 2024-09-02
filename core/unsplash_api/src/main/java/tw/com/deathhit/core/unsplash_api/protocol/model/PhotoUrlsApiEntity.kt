package tw.com.deathhit.core.unsplash_api.protocol.model

import com.google.gson.annotations.SerializedName

data class PhotoUrlsApiEntity(
    @SerializedName("small") val small: String
)
