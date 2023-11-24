package tw.com.deathhit.data.garden_planting

import android.content.Context
import androidx.paging.testing.asSnapshot
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import tw.com.deathhit.core.app_database.AppDatabase
import tw.com.deathhit.core.app_database.entity.GardenPlantingEntity
import tw.com.deathhit.data.garden_planting.config.buildAppDatabase
import tw.com.deathhit.data.garden_planting.config.generatePlantEntities

@OptIn(ExperimentalCoroutinesApi::class)
class GardenPlantingRepositoryTest {
    private lateinit var appDatabase: AppDatabase

    private val gardenPlantingDao get() = appDatabase.gardenPlantingDao()
    private val plantDao get() = appDatabase.plantDao()

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        appDatabase = buildAppDatabase(context)
    }

    @Test
    fun getGardenPlantingPagingDataFlow() = runTest {
        //Given
        val gardenPlantingRepository = GardenPlantingRepositoryImp(appDatabase = appDatabase)
        val plantEntities = generatePlantEntities().sortedBy { it.plantId }

        val gardenPlantingEntities = plantEntities.map {
            GardenPlantingEntity(
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

            assert(gardenPlanting.plantId == gardenPlantingEntity.plantId)
            assert(gardenPlanting.plantDate == gardenPlantingEntity.plantDate)
            assert(gardenPlanting.plantName == plantEntity.plantName)
        }
    }
}