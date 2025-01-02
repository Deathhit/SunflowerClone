package tw.com.deathhit.core.sunflower_clone_database

import android.content.Context
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import tw.com.deathhit.core.sunflower_clone_database.config.buildAppDatabase
import tw.com.deathhit.core.sunflower_clone_database.config.generatePlantEntities
import tw.com.deathhit.core.sunflower_clone_database.entity.GardenPlantingEntity

@OptIn(ExperimentalCoroutinesApi::class)
class GardenPlantingItemDaoTest {
    private lateinit var sunflowerCloneDatabase: SunflowerCloneDatabase

    private val gardenPlantingDao get() = sunflowerCloneDatabase.gardenPlantingDao()
    private val gardenPlantingItemDao get() = sunflowerCloneDatabase.gardenPlantingItemDao()
    private val plantDao get() = sunflowerCloneDatabase.plantDao()

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        sunflowerCloneDatabase = buildAppDatabase(context)
    }

    @Test
    fun getGardenPlantingItemsPagingSource() = runTest {
        //Given
        val plants = generatePlantEntities()

        gardenPlantingDao.upsert(plants.map {
            GardenPlantingEntity(
                plantId = it.plantId
            )
        })
        plantDao.upsert(plants)
        advanceUntilIdle()

        //When
        val gardenPlantingItems =
            (TestPager(
                PagingConfig(plants.size),
                gardenPlantingItemDao.getEntitiesPagingSource()
            ).refresh() as PagingSource.LoadResult.Page).data

        //Then
        gardenPlantingItems.forEachIndexed { index, gardenPlantingItem ->
            val plant = plants[index]

            assert(gardenPlantingItem.plantId == plant.plantId)
            assert(gardenPlantingItem.plantName == plant.plantName)
            assert(gardenPlantingItem.wateringIntervalDays == plant.wateringIntervalDays)
        }
    }
}