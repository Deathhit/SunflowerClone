package tw.com.deathhit.core.unsplash_api.protocol.model

import com.google.gson.annotations.SerializedName

data class UserApiEntity(
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String
)
