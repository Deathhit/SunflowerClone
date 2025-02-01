package tw.com.deathhit.data.garden_planting_kmp

import android.content.Context
import androidx.paging.testing.asSnapshot
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import tw.com.deathhit.core.sunflower_clone_database_kmp.GardenPlantingEntity
import tw.com.deathhit.core.sunflower_clone_database_kmp.SunflowerCloneDatabase
import tw.com.deathhit.core.sunflower_clone_database_kmp.gardenPlantingDao
import tw.com.deathhit.core.sunflower_clone_database_kmp.plantDao
import tw.com.deathhit.data.garden_planting_kmp.config.buildAppDatabase
import tw.com.deathhit.data.garden_planting_kmp.config.generatePlantEntities

@OptIn(ExperimentalCoroutinesApi::class)
class GardenPlantingRepositoryTest {
    private lateinit var sunflowerCloneDatabase: SunflowerCloneDatabase

    private val gardenPlantingDao get() = sunflowerCloneDatabase.gardenPlantingDao()
    private val plantDao get() = sunflowerCloneDatabase.plantDao()

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        sunflowerCloneDatabase = buildAppDatabase(context)
    }

    @Test
    fun getGardenPlantingPagingDataFlow() = runTest {
        //Given
        val gardenPlantingRepository = GardenPlantingRepositoryImp(sunflowerCloneDatabase = sunflowerCloneDatabase)
        val plantEntities = generatePlantEntities().sortedBy { it.plantId }

        val gardenPlantingEntities = plantEntities.map {
            GardenPlantingEntity(
                plant_id = it.plantId,
                garden_planting_id = 0,
                plant_date = System.currentTimeMillis()
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

            assert(gardenPlanting.plantId == gardenPlantingEntity.plantId)
            assert(gardenPlanting.plantDate == gardenPlantingEntity.plantDate)
            assert(gardenPlanting.plantName == plantEntity.plantName)
        }
    }
}