package tw.com.deathhit.data.sunflower_clone.plant

import tw.com.deathhit.core.sunflower_clone.app_database.view.PlantItemView
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class MapperExtTest {
    @Test
    fun mapPlantItemViewToPlantDO() {
        //Given
        val plantItemView = PlantItemView(
            description = getRandomStr(),
            growZoneNumber = getRandomInt(),
            imageUrl = getRandomStr(),
            plantDate = getRandomLong(),
            plantId = getRandomStr(),
            plantName = getRandomStr(),
            wateringIntervalDays = getRandomInt()
        )

        //When
        val plantDO = plantItemView.toPlantDO()

        //Then
        assertTrue(plantDO.description == plantItemView.description)
        assertTrue(plantDO.growZoneNumber == plantItemView.growZoneNumber)
        assertTrue(plantDO.imageUrl == plantItemView.imageUrl)
        assertTrue(plantDO.plantDate == plantItemView.plantDate)
        assertTrue(plantDO.plantId == plantItemView.plantId)
        assertTrue(plantDO.plantName == plantItemView.plantName)
        assertTrue(plantDO.wateringIntervalDays == plantItemView.wateringIntervalDays)
    }

    private fun getRandomInt() = Random.nextInt()
    private fun getRandomLong() = Random.nextLong()
    @OptIn(ExperimentalUuidApi::class)
    private fun getRandomStr() = Uuid.random().toString()
}