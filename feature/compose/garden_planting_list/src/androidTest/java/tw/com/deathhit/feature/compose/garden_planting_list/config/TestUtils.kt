package tw.com.deathhit.feature.compose.garden_planting_list.config

import tw.com.deathhit.domain.model.GardenPlantingDO
import java.util.UUID
import kotlin.random.Random

fun generateGardenPlantingDO(gardenPlantingId: Int, plantId: String) = GardenPlantingDO(
    description = getRandomStr(),
    gardenPlantingId = gardenPlantingId,
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