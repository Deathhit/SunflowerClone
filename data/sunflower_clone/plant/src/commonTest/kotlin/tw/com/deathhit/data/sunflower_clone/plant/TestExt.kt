package tw.com.deathhit.data.sunflower_clone.plant

import tw.com.deathhit.core.sunflower_clone.app_database.entity.PlantEntity
import kotlin.random.Random
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

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
@OptIn(ExperimentalUuidApi::class)
private fun getRandomStr() = Uuid.random().toString()