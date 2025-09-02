package tw.com.deathhit.data.sunflower_clone.photo

import tw.com.deathhit.core.sunflower_clone.app_database.view.PhotoItemView
import tw.com.deathhit.core.unsplash.api_client.protocol.model.PhotoApiEntity
import tw.com.deathhit.core.unsplash.api_client.protocol.model.PhotoUrlsApiEntity
import tw.com.deathhit.core.unsplash.api_client.protocol.model.UserApiEntity
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class MapperExtTest {
    @Test
    fun mapPhotoApiEntityListToPhotoEntityList() {
        //Given
        val page = getRandomInt()
        val pageSize = getRandomInt()
        val photoApiEntityList = listOf(
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
        val photoEntityList = photoApiEntityList.map { it.toPhotoEntity(plantName = plantName) }

        //Then
        val offset = (page - 1) * pageSize

        photoEntityList.forEachIndexed { index, entity ->
            with(photoApiEntityList[index]) {
                assertTrue { entity.authorId == user.username }
                assertTrue { entity.authorName == user.name }
                assertTrue { entity.imageUrl == urls.small }
                assertTrue { entity.photoId == id }
                assertTrue { entity.plantName == plantName }
            }
        }
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
        assertTrue { photoDO.attributionUrl == attributionUrl }
        assertTrue { photoDO.authorId == photoItemView.authorId }
        assertTrue { photoDO.authorName == photoItemView.authorName }
        assertTrue { photoDO.imageUrl == photoItemView.imageUrl }
        assertTrue { photoDO.photoId == photoItemView.photoId }
        assertTrue { photoDO.plantName == photoItemView.plantName }
    }

    private fun getRandomInt() = Random.nextInt()

    @OptIn(ExperimentalUuidApi::class)
    private fun getRandomStr() = Uuid.random().toString()
}