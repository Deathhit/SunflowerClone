package tw.com.deathhit.data.sunflower_clone.photo

import tw.com.deathhit.core.sunflower_clone.app_database.entity.PhotoEntity
import tw.com.deathhit.core.unsplash.api_client.protocol.model.PhotoApiEntity
import tw.com.deathhit.core.unsplash.api_client.protocol.model.PhotoUrlsApiEntity
import tw.com.deathhit.core.unsplash.api_client.protocol.model.UserApiEntity
import kotlin.random.Random
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

fun generatePhotoApiEntities(from: Int = 3, until: Int = 10) = mutableListOf<PhotoApiEntity>().apply {
    for (i in 0..getRandomInt(from = from, until = until)) {
        add(
            PhotoApiEntity(
                id = getRandomStr(),
                urls = PhotoUrlsApiEntity(small = getRandomStr()),
                user = UserApiEntity(name = getRandomStr(), username = getRandomStr())
            )
        )
    }
}.toList()

fun generatePhotoEntities(plantName: String? = null) = mutableListOf<PhotoEntity>().apply {
    for (i in 0..getRandomInt(from = 3, until = 10)) {
        add(
            PhotoEntity(
                authorId = getRandomStr(),
                authorName = getRandomStr(),
                imageUrl = getRandomStr(),
                photoId = i.toString(),
                plantName = plantName ?: getRandomStr(),
            )
        )
    }
}.toList()

fun generatePlantName() = getRandomStr()

private fun getRandomInt(from: Int = 0, until: Int = Int.MAX_VALUE) = Random.nextInt(from, until)
@OptIn(ExperimentalUuidApi::class)
private fun getRandomStr() = Uuid.random().toString()