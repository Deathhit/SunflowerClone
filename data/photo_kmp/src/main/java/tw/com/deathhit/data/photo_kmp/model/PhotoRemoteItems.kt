package tw.com.deathhit.data.photo_kmp.model

import tw.com.deathhit.core.sunflowerclonedatabasekmp.PhotoEntity
import tw.com.deathhit.core.sunflowerclonedatabasekmp.PhotoRemoteOrderEntity

internal data class PhotoRemoteItems(
    val photoEntity: PhotoEntity,
    val photoRemoteOrderEntity: PhotoRemoteOrderEntity
)
