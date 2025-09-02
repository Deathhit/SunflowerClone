package tw.com.deathhit.feature.compose.garden_planting_list.config

import tw.com.deathhit.domain.sunflower_clone.model.GardenPlantingDO
import java.util.UUID
import kotlin.random.Random

fun generateGardenPlantingDO(gardenPlantingId: Int, plantDate: Long, plantId: String) =
    GardenPlantingDO(
        description = getRandomStr(),
        gardenPlantingId = gardenPlantingId,
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