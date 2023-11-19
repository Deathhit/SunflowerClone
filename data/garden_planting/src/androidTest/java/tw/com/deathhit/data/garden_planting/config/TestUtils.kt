package tw.com.deathhit.data.garden_planting.config

import tw.com.deathhit.core.app_database.entity.PlantEntity
import java.util.UUID
import kotlin.random.Random

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