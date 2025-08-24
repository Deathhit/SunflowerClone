package tw.com.deathhit.data.photo.config

import android.content.Context
import androidx.room.Room
import tw.com.deathhit.core.sunflower_clone.app_database.SunflowerCloneDatabase
import tw.com.deathhit.core.sunflower_clone.app_database.entity.PhotoEntity
import tw.com.deathhit.core.unsplash.api_client.protocol.model.PhotoApiEntity
import tw.com.deathhit.core.unsplash.api_client.protocol.model.PhotoUrlsApiEntity
import tw.com.deathhit.core.unsplash.api_client.protocol.model.UserApiEntity
import java.util.UUID
import kotlin.random.Random

fun buildAppDatabase(context: Context) =
    Room.inMemoryDatabaseBuilder(context, SunflowerCloneDatabase::class.java).build()

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
private fun getRandomStr() = UUID.randomUUID().toString()