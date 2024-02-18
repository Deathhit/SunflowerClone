package tw.com.deathhit.feature.compose.gallery.config

import tw.com.deathhit.domain.model.PhotoDO
import java.util.UUID
import kotlin.random.Random

fun generatePhotoDOs(plantName: String) = mutableListOf<PhotoDO>().apply {
    for (i in 0..getRandomInt()) {
        add(
            PhotoDO(
                attributionUrl = getRandomStr(),
                authorId = getRandomStr(),
                authorName = getRandomStr(),
                imageUrl = getRandomStr(),
                photoId = i.toString(),
                plantName = plantName,
            )
        )
    }
}.toList()

fun generatePlantName() = getRandomStr()

fun generateUrl() = getRandomStr()

private fun getRandomInt() = Random.nextInt(3, 10)
private fun getRandomStr() = UUID.randomUUID().toString()