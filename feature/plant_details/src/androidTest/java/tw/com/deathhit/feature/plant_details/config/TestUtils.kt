package tw.com.deathhit.feature.plant_details.config

import tw.com.deathhit.domain.model.PlantDO
import java.util.UUID
import kotlin.random.Random

fun generatePlantDO(plantId: String) = PlantDO(
    description = getRandomStr(),
    growZoneNumber = getRandomInt(),
    imageUrl = getRandomStr(),
    plantDate = getRandomLong(),
    plantId = plantId,
    plantName = getRandomStr(),
    wateringIntervalDays = getRandomInt()
)

fun generatePlantId() = getRandomStr()

private fun getRandomInt() = Random.nextInt(3, 10)
private fun getRandomLong() = Random.nextLong()
private fun getRandomStr() = UUID.randomUUID().toString()