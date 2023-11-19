package tw.com.deathhit.data.garden_planting

import androidx.paging.testing.asSnapshot
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import tw.com.deathhit.core.app_database.AppDatabase
import tw.com.deathhit.core.app_database.entity.GardenPlantingEntity
import tw.com.deathhit.data.garden_planting.config.generatePlantEntities
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class GardenPlantingRepositoryTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    internal lateinit var appDatabase: AppDatabase

    private val gardenPlantingDao get() = appDatabase.gardenPlantingDao()
    private val plantDao get() = appDatabase.plantDao()

    @Before
    fun setup() {
        hiltRule.inject()
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