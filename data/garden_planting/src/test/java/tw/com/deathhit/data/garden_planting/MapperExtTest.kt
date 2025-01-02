package tw.com.deathhit.data.garden_planting

import org.junit.Test
import tw.com.deathhit.core.sunflower_clone_database.view.GardenPlantingItemView
import java.util.UUID
import kotlin.random.Random

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
        assert(gardenPlantingDO.description == gardenPlantingItemView.description)
        assert(gardenPlantingDO.gardenPlantingId == gardenPlantingItemView.gardenPlantingId)
        assert(gardenPlantingDO.growZoneNumber == gardenPlantingItemView.growZoneNumber)
        assert(gardenPlantingDO.imageUrl == gardenPlantingItemView.imageUrl)
        assert(gardenPlantingDO.plantDate == gardenPlantingItemView.plantDate)
        assert(gardenPlantingDO.plantId == gardenPlantingItemView.plantId)
        assert(gardenPlantingDO.plantName == gardenPlantingItemView.plantName)
        assert(gardenPlantingDO.wateringIntervalDays == gardenPlantingItemView.wateringIntervalDays)
    }

    private fun getRandomInt() = Random.nextInt()
    private fun getRandomLong() = Random.nextLong()
    private fun getRandomStr() = UUID.randomUUID().toString()
}