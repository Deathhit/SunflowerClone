package tw.com.deathhit.data.plant

import androidx.paging.testing.asSnapshot
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import tw.com.deathhit.core.app_database.AppDatabase
import tw.com.deathhit.core.app_database.entity.PlantEntity
import tw.com.deathhit.data.plant.config.generatePlantEntities
import tw.com.deathhit.domain.model.PlantDO
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class PlantRepositoryTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    internal lateinit var appDatabase: AppDatabase

    private val plantDao get() = appDatabase.plantDao()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun getPlantFlow() = runTest {
        //Given
        val plantEntities = generatePlantEntities()
        val plantRepository = PlantRepositoryImp(appDatabase = appDatabase)

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
        val plantRepository = PlantRepositoryImp(appDatabase = appDatabase)

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