package tw.com.deathhit.feature.plant_details.config

import tw.com.deathhit.domain.sunflower_clone.model.PlantDO
import java.util.UUID
import kotlin.random.Random

fun generatePlantDO(plantDate: Long, plantId: String) = PlantDO(
    description = getRandomStr(),
    growZoneNumber = getRandomInt(),
    imageUrl = getRandomStr(),
    plantDate = plantDate,
    plantId = plantId,
    plantName = getRandomStr(),
    wateringIntervalDays = getRandomInt()
)

fun generatePlantId() = getRandomStr()

private fun getRandomInt() = Random.nextInt(3, 10)
private fun getRandomStr() = UUID.randomUUID().toString()