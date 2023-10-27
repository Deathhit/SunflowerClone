package tw.com.deathhit.core.unsplash_api.protocol.model

import com.google.gson.annotations.SerializedName

internal data class User(
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String
)
