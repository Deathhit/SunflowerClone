package tw.com.deathhit.data.photo

import tw.com.deathhit.core.sunflower_clone.app_database.entity.PhotoEntity
import tw.com.deathhit.core.sunflower_clone.app_database.view.PhotoItemView
import tw.com.deathhit.core.unsplash.api_client.protocol.model.PhotoApiEntity
import tw.com.deathhit.domain.model.PhotoDO

internal fun PhotoApiEntity.toPhotoEntity(
    plantName: String
): PhotoEntity = PhotoEntity(
    authorId = user.username,
    authorName = user.name,
    imageUrl = urls.small,
    photoId = id,
    plantName = plantName
)

internal fun PhotoItemView.toPhotoDO(attributionUrl: String) =
    PhotoDO(
        attributionUrl = attributionUrl,
        authorId = authorId,
        authorName = authorName,
        imageUrl = imageUrl,
        photoId = photoId,
        plantName = plantName
    )