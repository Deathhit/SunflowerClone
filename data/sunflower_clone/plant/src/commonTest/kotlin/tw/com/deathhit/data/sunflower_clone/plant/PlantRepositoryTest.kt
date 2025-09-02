package tw.com.deathhit.data.sunflower_clone.plant

import androidx.paging.testing.asSnapshot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import tw.com.deathhit.core.sunflower_clone.app_database.SunflowerCloneDatabase
import tw.com.deathhit.core.sunflower_clone.app_database.entity.PlantEntity
import tw.com.deathhit.domain.sunflower_clone.model.PlantDO
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
abstract class PlantRepositoryTest {
    private lateinit var sunflowerCloneDatabase: SunflowerCloneDatabase

    private val plantDao get() = sunflowerCloneDatabase.plantDao()

    @BeforeTest
    fun setup() {
        sunflowerCloneDatabase = createAppDatabase()
    }

    @Test
    fun getPlantFlow() = runTest {
        //Given
        val plantEntities = generatePlantEntities()
        val plantRepository = PlantRepositoryImp(sunflowerCloneDatabase = sunflowerCloneDatabase)

        val plantEntity = plantEntities.random()

        plantDao.upsert(plantEntities)
        advanceUntilIdle()

        //When
        val plant = plantRepository.getPlantFlow(plantId = plantEntity.plantId).first()!!

        //Then
        plant.assertEqualsToEntity(plantEntity)
    }

    @Test
    fun getPlantPagingDataFlow() = runTest {
        //Given
        val plantEntities = generatePlantEntities().sortedBy { it.plantId }
        val plantRepository = PlantRepositoryImp(sunflowerCloneDatabase = sunflowerCloneDatabase)

        plantDao.upsert(plantEntities)
        advanceUntilIdle()

        //When
        val plants = plantRepository.getPlantPagingDataFlow()
        val plantsSnapshot = plants.asSnapshot {
            scrollTo(plantEntities.size)
        }.sortedBy { it.plantId }

        //Then
        plantsSnapshot.forEachIndexed { index, plant ->
            val plantEntity = plantEntities[index]

            plant.assertEqualsToEntity(plantEntity)
        }
    }

    private fun PlantDO.assertEqualsToEntity(entity: PlantEntity) {
        assertTrue(description == entity.description)
        assertTrue(growZoneNumber == entity.growZoneNumber)
        assertTrue(imageUrl == entity.imageUrl)
        assertTrue(plantId == entity.plantId)
        assertTrue(plantName == entity.plantName)
        assertTrue(wateringIntervalDays == entity.wateringIntervalDays)
    }

    protected abstract fun createAppDatabase(): SunflowerCloneDatabase
}