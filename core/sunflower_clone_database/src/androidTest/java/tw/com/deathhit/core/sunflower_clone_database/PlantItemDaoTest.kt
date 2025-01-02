package tw.com.deathhit.core.sunflower_clone_database

import android.content.Context
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import tw.com.deathhit.core.sunflower_clone_database.config.buildAppDatabase
import tw.com.deathhit.core.sunflower_clone_database.config.generatePlantEntities
import tw.com.deathhit.core.sunflower_clone_database.entity.PlantEntity
import tw.com.deathhit.core.sunflower_clone_database.view.PlantItemView

@OptIn(ExperimentalCoroutinesApi::class)
class PlantItemDaoTest {
    private lateinit var sunflowerCloneDatabase: SunflowerCloneDatabase

    private val plantDao get() = sunflowerCloneDatabase.plantDao()
    private val plantItemDao get() = sunflowerCloneDatabase.plantItemDao()

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        sunflowerCloneDatabase = buildAppDatabase(context)
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