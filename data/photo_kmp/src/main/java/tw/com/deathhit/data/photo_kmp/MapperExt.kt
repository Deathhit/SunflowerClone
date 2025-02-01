package tw.com.deathhit.data.photo_kmp

import tw.com.deathhit.core.sunflowerclonedatabasekmp.PhotoEntity
import tw.com.deathhit.core.sunflowerclonedatabasekmp.PhotoItemView
import tw.com.deathhit.core.sunflowerclonedatabasekmp.PhotoRemoteOrderEntity
import tw.com.deathhit.core.unsplash_api.protocol.model.PhotoApiEntity
import tw.com.deathhit.data.photo_kmp.model.PhotoRemoteItems
import tw.com.deathhit.domain.model.PhotoDO

internal fun List<PhotoApiEntity>.toPhotoRemoteItemsList(
    page: Int,
    pageSize: Int,
    plantName: String
): List<PhotoRemoteItems> {
    val offset = (page - 1) * pageSize

    return mapIndexed { index, photo ->
        with(photo) {
            PhotoRemoteItems(
                photoEntity = PhotoEntity(
                    author_id = user.username,
                    author_name = user.name,
                    image_url = urls.small,
                    photo_id = id,
                    plant_name = plantName
                ),
                photoRemoteOrderEntity = PhotoRemoteOrderEntity(
                    photo_id = id,
                    remote_order = (index + offset).toLong()
                )
            )
        }
    }
}

internal fun PhotoItemView.toPhotoDO(attributionUrl: String) =
    PhotoDO(
        attributionUrl = attributionUrl,
        authorId = author_id,
        authorName = author_name,
        imageUrl = image_url,
        photoId = photo_id,
        plantName = plant_name
    )