package tw.com.deathhit.data.photo

import tw.com.deathhit.core.app_database.entity.PhotoEntity
import tw.com.deathhit.core.app_database.entity.PhotoRemoteOrderEntity
import tw.com.deathhit.core.app_database.view.PhotoItemView
import tw.com.deathhit.core.unsplash_api.model.Photo
import tw.com.deathhit.domain.model.PhotoDO

internal fun List<Photo>.toPhotoRemoteItems(plantId: String) = mapIndexed { index, photo ->
    PhotoRemoteItems(
        photoEntity = PhotoEntity(
            authorId = photo.authorId,
            authorName = photo.authorName,
            imageUrl = photo.url,
            photoId = photo.photoId,
            plantId = plantId
        ),
        photoRemoteOrderEntity = PhotoRemoteOrderEntity(
            photoId = photo.photoId,
            remoteOrder = index
        )
    )
}

internal fun PhotoItemView.toPhotoDO(attributionUrl: String) = PhotoDO(
    attributionUrl = attributionUrl,
    authorId = authorId,
    authorName = authorName,
    imageUrl = imageUrl,
    photoId = photoId,
    plantId = plantId
)