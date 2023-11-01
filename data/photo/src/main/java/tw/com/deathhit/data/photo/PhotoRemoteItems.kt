package tw.com.deathhit.data.photo

import tw.com.deathhit.core.app_database.entity.PhotoEntity
import tw.com.deathhit.core.app_database.entity.PhotoRemoteOrderEntity

internal data class PhotoRemoteItems(
    val photoEntity: PhotoEntity,
    val photoRemoteOrderEntity: PhotoRemoteOrderEntity
)
