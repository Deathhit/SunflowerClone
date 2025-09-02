package tw.com.deathhit.feature.plant_list.config

import tw.com.deathhit.domain.sunflower_clone.model.PlantDO
import java.util.UUID
import kotlin.random.Random

fun generatePlantDOs() = mutableListOf<PlantDO>().apply {
    for (i in 0..getRandomInt()) {
        add(
            PlantDO(
                description = getRandomStr(),
                growZoneNumber = getRandomInt(),
                imageUrl = getRandomStr(),
                plantDate = getRandomLong(),
                plantId = i.toString(),
                plantName = getRandomStr(),
                wateringIntervalDays = getRandomInt()
            )
        )
    }
}.toList()

fun generatePlantId() = getRandomStr()

private fun getRandomInt() = Random.nextInt(3, 10)
private fun getRandomLong() = Random.nextLong()
private fun getRandomStr() = UUID.randomUUID().toString()