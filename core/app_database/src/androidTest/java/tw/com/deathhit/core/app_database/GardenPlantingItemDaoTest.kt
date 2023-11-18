package tw.com.deathhit.core.app_database

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import tw.com.deathhit.core.app_database.config.generatePlantEntities
import tw.com.deathhit.core.app_database.entity.GardenPlantingEntity
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class GardenPlantingItemDaoTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    internal lateinit var appDatabase: AppDatabase

    private val gardenPlantingDao get() = appDatabase.gardenPlantingDao()
    private val gardenPlantingItemDao get() = appDatabase.gardenPlantingItemDao()
    private val plantDao get() = appDatabase.plantDao()

    @Before
    fun setup() {
        hiltRule.inject()
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