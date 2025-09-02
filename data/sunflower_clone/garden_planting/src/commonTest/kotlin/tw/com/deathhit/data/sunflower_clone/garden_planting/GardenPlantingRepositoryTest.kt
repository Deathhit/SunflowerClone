package tw.com.deathhit.data.sunflower_clone.garden_planting

import androidx.paging.testing.asSnapshot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import tw.com.deathhit.core.sunflower_clone.app_database.SunflowerCloneDatabase
import tw.com.deathhit.core.sunflower_clone.app_database.entity.GardenPlantingEntity
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalCoroutinesApi::class)
abstract class GardenPlantingRepositoryTest {
    private lateinit var sunflowerCloneDatabase: SunflowerCloneDatabase

    private val gardenPlantingDao get() = sunflowerCloneDatabase.gardenPlantingDao()
    private val plantDao get() = sunflowerCloneDatabase.plantDao()

    @BeforeTest
    fun setup() {
        sunflowerCloneDatabase = createAppDatabase()
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun getGardenPlantingPagingDataFlow() = runTest {
        //Given
        val gardenPlantingRepository =
            GardenPlantingRepositoryImp(sunflowerCloneDatabase = sunflowerCloneDatabase)
        val plantEntities = generatePlantEntities().sortedBy { it.plantId }

        val gardenPlantingEntities = plantEntities.map {
            GardenPlantingEntity(
                plantDate = Clock.System.now().toEpochMilliseconds(),
                plantId = it.plantId
            )
        }

        gardenPlantingDao.upsert(gardenPlantingEntities)
        plantDao.upsert(plantEntities)
        advanceUntilIdle()

        //When
        val gardenPlantings = gardenPlantingRepository.getGardenPlantingPagingDataFlow()
        val gardenPlantingsSnapshot = gardenPlantings.asSnapshot {
            scrollTo(gardenPlantingEntities.size)
        }.sortedBy { it.plantId }

        //Then
        gardenPlantingsSnapshot.forEachIndexed { index, gardenPlanting ->
            val gardenPlantingEntity = gardenPlantingEntities[index]
            val plantEntity = plantEntities[index]

            assertTrue { gardenPlanting.plantId == gardenPlantingEntity.plantId }
            assertTrue { gardenPlanting.plantDate == gardenPlantingEntity.plantDate }
            assertTrue { gardenPlanting.plantName == plantEntity.plantName }
        }
    }

    protected abstract fun createAppDatabase(): SunflowerCloneDatabase
}