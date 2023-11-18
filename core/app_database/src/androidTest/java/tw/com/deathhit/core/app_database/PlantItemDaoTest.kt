package tw.com.deathhit.core.app_database

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import tw.com.deathhit.core.app_database.config.generatePlantEntities
import tw.com.deathhit.core.app_database.entity.PlantEntity
import tw.com.deathhit.core.app_database.view.PlantItemView
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class PlantItemDaoTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    internal lateinit var appDatabase: AppDatabase

    private val plantDao get() = appDatabase.plantDao()
    private val plantItemDao get() = appDatabase.plantItemDao()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun getPlantItemFlow() = runTest {
        //Given
        val plants = generatePlantEntities()

        val plant = plants.random()

        plantDao.upsert(plants)
        advanceUntilIdle()

        //When
        val plantItem = plantItemDao.getEntityFlow(plantId = plant.plantId).first()!!

        //Then
        plantItem.assertEqualsToEntity(plant)
    }

    @Test
    fun getPlantItemsPagingSource() = runTest {
        //Given
        val plants = generatePlantEntities()

        val sortedPlants = plants.sortedBy { it.plantName }

        plantDao.upsert(plants)
        advanceUntilIdle()

        //When
        val plantItems =
            (TestPager(
                PagingConfig(plants.size),
                plantItemDao.getEntitiesPagingSource()
            ).refresh() as PagingSource.LoadResult.Page).data

        //Then
        plantItems.forEachIndexed { index, plantItem ->
            plantItem.assertEqualsToEntity(sortedPlants[index])
        }
    }

    private fun PlantItemView.assertEqualsToEntity(entity: PlantEntity) {
        assert(description == entity.description)
        assert(growZoneNumber == entity.growZoneNumber)
        assert(imageUrl == entity.imageUrl)
        assert(plantId == entity.plantId)
        assert(plantName == entity.plantName)
        assert(wateringIntervalDays == entity.wateringIntervalDays)
    }
}