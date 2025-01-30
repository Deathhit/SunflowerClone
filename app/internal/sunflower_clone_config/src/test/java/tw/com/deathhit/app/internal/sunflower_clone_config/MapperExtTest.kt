package tw.com.deathhit.app.internal.sunflower_clone_config

import org.junit.Test
import tw.com.deathhit.app.internal.sunflower_clone_config.model.PlantJson
import java.util.UUID
import kotlin.random.Random

class MapperExtTest {
    @Test
    fun mapPlantJsonToPlantEntity() {
        //Given
        val plantJson = PlantJson(
            description = getRandomStr(),
            growZoneNumber = getRandomInt(),
            imageUrl = getRandomStr(),
            name = getRandomStr(),
            plantId = getRandomStr(),
            wateringInterval = getRandomInt()
        )

        //When
        val plantEntity = plantJson.toPlantEntity()

        //Then
        assert(plantEntity.description == plantJson.description)
        assert(plantEntity.growZoneNumber == plantJson.growZoneNumber)
        assert(plantEntity.imageUrl == plantJson.imageUrl)
        assert(plantEntity.plantId == plantJson.plantId)
        assert(plantEntity.plantName == plantJson.name)
        assert(plantEntity.wateringIntervalDays == plantJson.wateringInterval)
    }

    private fun getRandomInt() = Random.nextInt()
    private fun getRandomStr() = UUID.randomUUID().toString()
}