package tw.com.deathhit.data.photo

import org.junit.Test
import tw.com.deathhit.core.sunflower_clone_database.view.PhotoItemView
import tw.com.deathhit.core.unsplash_api.protocol.model.PhotoApiEntity
import tw.com.deathhit.core.unsplash_api.protocol.model.PhotoUrlsApiEntity
import tw.com.deathhit.core.unsplash_api.protocol.model.UserApiEntity
import java.util.UUID
import kotlin.random.Random

class MapperExtTest {
    @Test
    fun mapPhotoApiEntityListToPhotoRemoteItemsList() {
        //Given
        val page = getRandomInt()
        val pageSize = getRandomInt()
        val photoDtoList = listOf(
            PhotoApiEntity(
                id = getRandomStr(),
                urls = PhotoUrlsApiEntity(small = getRandomStr()),
                user = UserApiEntity(name = getRandomStr(), username = getRandomStr())
            ),
            PhotoApiEntity(
                id = getRandomStr(),
                urls = PhotoUrlsApiEntity(small = getRandomStr()),
                user = UserApiEntity(name = getRandomStr(), username = getRandomStr())
            ),
            PhotoApiEntity(
                id = getRandomStr(),
                urls = PhotoUrlsApiEntity(small = getRandomStr()),
                user = UserApiEntity(name = getRandomStr(), username = getRandomStr())
            ),
        )
        val plantName = getRandomStr()

        //When
        val photoRemoteItemsList = photoDtoList.toPhotoRemoteItemsList(
            page = page,
            pageSize = pageSize,
            plantName = plantName
        )

        //Then
        val offset = (page - 1) * pageSize

        assert(photoRemoteItemsList.filterIndexed { index, photoRemoteItems ->
            val photoDto = photoDtoList[index]

            with(photoRemoteItems.photoEntity) {
                authorId == photoDto.user.username
                        && authorName == photoDto.user.name
                        && imageUrl == photoDto.urls.small
                        && photoId == photoDto.id
            } && with(photoRemoteItems.photoRemoteOrderEntity) {
                photoId == photoDto.id && remoteOrder == index + offset
            }
        }.size == photoDtoList.size)
    }

    @Test
    fun mapPhotoItemViewToPhotoDO() {
        //Given
        val attributionUrl = getRandomStr()
        val photoItemView = PhotoItemView(
            authorId = getRandomStr(),
            authorName = getRandomStr(),
            imageUrl = getRandomStr(),
            photoId = getRandomStr(),
            plantName = getRandomStr(),
            remoteOrder = getRandomInt()
        )

        //When
        val photoDO = photoItemView.toPhotoDO(attributionUrl = attributionUrl)

        //Then
        assert(photoDO.attributionUrl == attributionUrl)
        assert(photoDO.authorId == photoItemView.authorId)
        assert(photoDO.authorName == photoItemView.authorName)
        assert(photoDO.imageUrl == photoItemView.imageUrl)
        assert(photoDO.photoId == photoItemView.photoId)
        assert(photoDO.plantName == photoItemView.plantName)
    }

    private fun getRandomInt() = Random.nextInt()
    private fun getRandomStr() = UUID.randomUUID().toString()
}