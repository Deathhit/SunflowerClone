package tw.com.deathhit.data.photo.config

import tw.com.deathhit.core.app_database.entity.PhotoEntity
import tw.com.deathhit.core.unsplash_api.model.PhotoDto
import java.util.UUID
import kotlin.random.Random

fun generatePhotoDtoList(from: Int = 3, until: Int = 10) = mutableListOf<PhotoDto>().apply {
    for (i in 0..getRandomInt(from = from, until = until)) {
        add(
            PhotoDto(
                authorId = getRandomStr(),
                authorName = getRandomStr(),
                photoId = i.toString(),
                url = getRandomStr()
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
private fun getRandomStr() = UUID.randomUUID().toString()