package tw.com.deathhit.data.plant

import android.content.Context
import androidx.paging.testing.asSnapshot
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import tw.com.deathhit.core.sunflower_clone.app_database.SunflowerCloneDatabase
import tw.com.deathhit.core.sunflower_clone.app_database.entity.PlantEntity
import tw.com.deathhit.data.plant.config.buildAppDatabase
import tw.com.deathhit.data.plant.config.generatePlantEntities
import tw.com.deathhit.domain.model.PlantDO

@OptIn(ExperimentalCoroutinesApi::class)
class PlantRepositoryTest {
    private lateinit var sunflowerCloneDatabase: SunflowerCloneDatabase

    private val plantDao get() = sunflowerCloneDatabase.plantDao()

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        sunflowerCloneDatabase = buildAppDatabase(context)
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
        assert(description == entity.description)
        assert(growZoneNumber == entity.growZoneNumber)
        assert(imageUrl == entity.imageUrl)
        assert(plantId == entity.plantId)
        assert(plantName == entity.plantName)
        assert(wateringIntervalDays == entity.wateringIntervalDays)
    }
}