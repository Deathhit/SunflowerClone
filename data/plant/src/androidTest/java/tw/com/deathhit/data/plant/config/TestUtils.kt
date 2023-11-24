package tw.com.deathhit.data.plant.config

import android.content.Context
import androidx.room.Room
import tw.com.deathhit.core.app_database.AppDatabase
import tw.com.deathhit.core.app_database.entity.PlantEntity
import java.util.UUID
import kotlin.random.Random

fun buildAppDatabase(context: Context) =
    Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()

fun generatePlantEntities() = mutableListOf<PlantEntity>().apply {
    for (i in 0..getRandomInt(from = 3, until = 10)) {
        add(
            PlantEntity(
                description = getRandomStr(),
                growZoneNumber = i,
                imageUrl = getRandomStr(),
                plantId = i.toString(),
                plantName = getRandomStr(),
                wateringIntervalDays = getRandomInt()
            )
        )
    }
}.toList()

private fun getRandomInt(from: Int = 0, until: Int = Int.MAX_VALUE) = Random.nextInt(from, until)
private fun getRandomStr() = UUID.randomUUID().toString()