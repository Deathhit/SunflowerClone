package tw.com.deathhit.core.unsplash_api

import org.junit.Test
import tw.com.deathhit.core.unsplash_api.protocol.model.Photo
import tw.com.deathhit.core.unsplash_api.protocol.model.PhotoUrls
import tw.com.deathhit.core.unsplash_api.protocol.model.User
import java.util.UUID

class MapperExtTest {
    @Test
    fun mapPhotoToPhotoDto() {
        //Given
        val photo = Photo(
            id = getRandomStr(),
            urls = PhotoUrls(
                small = getRandomStr()
            ),
            user = User(
                name = getRandomStr(),
                username = getRandomStr()
            )
        )

        //When
        val photoDto = photo.toPhotoDto()

        //Then
        assert(photoDto.authorId == photo.user.username)
        assert(photoDto.authorName == photo.user.name)
        assert(photoDto.photoId == photo.id)
        assert(photoDto.url == photo.urls.small)
    }

    private fun getRandomStr() = UUID.randomUUID().toString()
}