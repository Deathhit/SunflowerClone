package tw.com.deathhit.data.photo

import org.junit.Test
import tw.com.deathhit.core.app_database.view.PhotoItemView
import tw.com.deathhit.core.unsplash_api.model.PhotoDto
import java.util.UUID
import kotlin.random.Random

class MapperExtTest {
    @Test
    fun mapPhotoDtoListToPhotoRemoteItemsList() {
        //Given
        val page = getRandomInt()
        val pageSize = getRandomInt()
        val photoDtoList = listOf(
            PhotoDto(
                authorId = getRandomStr(),
                authorName = getRandomStr(),
                photoId = getRandomStr(),
                url = getRandomStr()
            ),
            PhotoDto(
                authorId = getRandomStr(),
                authorName = getRandomStr(),
                photoId = getRandomStr(),
                url = getRandomStr()
            ),
            PhotoDto(
                authorId = getRandomStr(),
                authorName = getRandomStr(),
                photoId = getRandomStr(),
                url = getRandomStr()
            )
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
                authorId == photoDto.authorId
                        && authorName == photoDto.authorName
                        && imageUrl == photoDto.url
                        && photoId == photoDto.photoId
            } && with(photoRemoteItems.photoRemoteOrderEntity) {
                photoId == photoDto.photoId && remoteOrder == index + offset
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