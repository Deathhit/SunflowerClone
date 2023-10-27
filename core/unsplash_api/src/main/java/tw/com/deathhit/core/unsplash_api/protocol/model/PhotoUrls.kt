package tw.com.deathhit.core.unsplash_api.protocol.model

import com.google.gson.annotations.SerializedName

internal data class PhotoUrls(
    @SerializedName("small") val small: String
)
