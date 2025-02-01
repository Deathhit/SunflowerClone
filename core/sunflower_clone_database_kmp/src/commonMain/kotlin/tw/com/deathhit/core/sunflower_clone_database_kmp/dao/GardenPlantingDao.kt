package tw.com.deathhit.core.sunflower_clone_database_kmp.dao

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tw.com.deathhit.core.sunflower_clone_database_kmp.SunflowerCloneDatabase
import tw.com.deathhit.core.sunflowerclonedatabasekmp.GardenPlantingEntity

interface GardenPlantingDao {
    suspend fun upsert(entities: List<GardenPlantingEntity>)

    class Imp(private val database: SunflowerCloneDatabase) : GardenPlantingDao {
        private val queries = database.gardenPlantingEntityQueries

        override suspend fun upsert(entities: List<GardenPlantingEntity>) {
            withContext(Dispatchers.IO) {
                database.transaction {
                    entities.forEach {
                        queries.upsert(
                            garden_planting_id = null,
                            plant_date = it.plant_date,
                            plant_id = it.plant_id
                        )
                    }
                }
            }
        }
    }
}