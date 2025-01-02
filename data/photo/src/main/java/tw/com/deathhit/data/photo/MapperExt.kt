package tw.com.deathhit.data.photo

import tw.com.deathhit.core.sunflower_clone_database.view.PhotoItemView
import tw.com.deathhit.core.unsplash_api.protocol.model.PhotoApiEntity
import tw.com.deathhit.data.photo.model.PhotoRemoteItems
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
                photoEntity = tw.com.deathhit.core.sunflower_clone_database.entity.PhotoEntity(
                    authorId = user.username,
                    authorName = user.name,
                    imageUrl = urls.small,
                    photoId = id,
                    plantName = plantName
                ),
                photoRemoteOrderEntity = tw.com.deathhit.core.sunflower_clone_database.entity.PhotoRemoteOrderEntity(
                    photoId = id,
                    remoteOrder = index + offset
                )
            )
        }
    }
}

internal fun PhotoItemView.toPhotoDO(attributionUrl: String) =
    PhotoDO(
        attributionUrl = attributionUrl,
        authorId = authorId,
        authorName = authorName,
        imageUrl = imageUrl,
        photoId = photoId,
        plantName = plantName
    )