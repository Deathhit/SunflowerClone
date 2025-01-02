package tw.com.deathhit.data.photo.model

import tw.com.deathhit.core.sunflower_clone_database.entity.PhotoEntity
import tw.com.deathhit.core.sunflower_clone_database.entity.PhotoRemoteOrderEntity

internal data class PhotoRemoteItems(
    val photoEntity: PhotoEntity,
    val photoRemoteOrderEntity: PhotoRemoteOrderEntity
)
