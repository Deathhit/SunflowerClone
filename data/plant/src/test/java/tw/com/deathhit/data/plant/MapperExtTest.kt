package tw.com.deathhit.data.plant

import org.junit.Test
import tw.com.deathhit.core.sunflower_clone.app_database.view.PlantItemView
import java.util.UUID
import kotlin.random.Random

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
        assert(plantDO.description == plantItemView.description)
        assert(plantDO.growZoneNumber == plantItemView.growZoneNumber)
        assert(plantDO.imageUrl == plantItemView.imageUrl)
        assert(plantDO.plantDate == plantItemView.plantDate)
        assert(plantDO.plantId == plantItemView.plantId)
        assert(plantDO.plantName == plantItemView.plantName)
        assert(plantDO.wateringIntervalDays == plantItemView.wateringIntervalDays)
    }

    private fun getRandomInt() = Random.nextInt()
    private fun getRandomLong() = Random.nextLong()
    private fun getRandomStr() = UUID.randomUUID().toString()
}