package tw.com.deathhit.data.photo

import tw.com.deathhit.core.app_database.entity.PhotoEntity
import tw.com.deathhit.core.app_database.entity.PhotoRemoteOrderEntity
import tw.com.deathhit.core.app_database.view.PhotoItemView
import tw.com.deathhit.core.unsplash_api.model.Photo
import tw.com.deathhit.domain.model.PhotoDO

internal fun List<Photo>.toPhotoRemoteItem(
    page: Int,
    pageSize: Int,
    plantName: String
): List<PhotoRemoteItems> {
    val offset = (page - 1) * pageSize

    return mapIndexed { index, photo ->
        with(photo) {
            PhotoRemoteItems(
                photoEntity = PhotoEntity(
                    authorId = authorId,
                    authorName = authorName,
                    imageUrl = url,
                    photoId = photoId,
                    plantName = plantName
                ),
                photoRemoteOrderEntity = PhotoRemoteOrderEntity(
                    photoId = photoId,
                    remoteOrder = index + offset
                )
            )
        }
    }
}

internal fun PhotoItemView.toPhotoDO(attributionUrl: String) = PhotoDO(
    attributionUrl = attributionUrl,
    authorId = authorId,
    authorName = authorName,
    imageUrl = imageUrl,
    photoId = photoId,
    plantId = plantName
)