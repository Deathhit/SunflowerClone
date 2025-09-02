package tw.com.deathhit.data.sunflower_clone.garden_planting

import tw.com.deathhit.core.sunflower_clone.app_database.view.GardenPlantingItemView
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class MapperExtTest {
    @Test
    fun mapGardenPlantingItemViewToGardenPlantingDO() {
        //Given
        val gardenPlantingItemView = GardenPlantingItemView(
            description = getRandomStr(),
            gardenPlantingId = getRandomInt(),
            growZoneNumber = getRandomInt(),
            imageUrl = getRandomStr(),
            plantDate = getRandomLong(),
            plantId = getRandomStr(),
            plantName = getRandomStr(),
            wateringIntervalDays = getRandomInt()
        )

        //When
        val gardenPlantingDO = gardenPlantingItemView.toGardenPlantingDO()

        //Then
        assertTrue { gardenPlantingDO.description == gardenPlantingItemView.description }
        assertTrue { gardenPlantingDO.gardenPlantingId == gardenPlantingItemView.gardenPlantingId }
        assertTrue { gardenPlantingDO.growZoneNumber == gardenPlantingItemView.growZoneNumber }
        assertTrue { gardenPlantingDO.imageUrl == gardenPlantingItemView.imageUrl }
        assertTrue { gardenPlantingDO.plantDate == gardenPlantingItemView.plantDate }
        assertTrue { gardenPlantingDO.plantId == gardenPlantingItemView.plantId }
        assertTrue { gardenPlantingDO.plantName == gardenPlantingItemView.plantName }
        assertTrue { gardenPlantingDO.wateringIntervalDays == gardenPlantingItemView.wateringIntervalDays }
    }

    private fun getRandomInt() = Random.nextInt()
    private fun getRandomLong() = Random.nextLong()
    @OptIn(ExperimentalUuidApi::class)
    private fun getRandomStr() = Uuid.random().toString()
}