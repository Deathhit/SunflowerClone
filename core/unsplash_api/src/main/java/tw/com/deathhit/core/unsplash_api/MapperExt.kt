package tw.com.deathhit.core.unsplash_api

import tw.com.deathhit.core.unsplash_api.model.PhotoDto
import tw.com.deathhit.core.unsplash_api.protocol.model.Photo

internal fun Photo.toPhotoDto() = PhotoDto(
    authorName = user.name,
    authorId = user.username,
    photoId = id,
    url = urls.small
)