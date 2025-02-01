package tw.com.deathhit.core.sunflower_clone_database_kmp.dao

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tw.com.deathhit.core.sunflower_clone_database_kmp.SunflowerCloneDatabase
import tw.com.deathhit.core.sunflowerclonedatabasekmp.PlantEntity

interface PlantDao {
    suspend fun upsert(entities: List<PlantEntity>)

    class Imp(private val database: SunflowerCloneDatabase) : PlantDao {
        private val queries = database.plantEntityQueries

        override suspend fun upsert(entities: List<PlantEntity>) {
            withContext(Dispatchers.IO) {
                database.transaction {
                    entities.forEach {
                        queries.upsert(
                            plant_id = it.plant_id,
                            description = it.description,
                            grow_zone_number = it.grow_zone_number,
                            image_url = it.image_url,
                            plant_name = it.plant_name,
                            watering_interval_days = it.watering_interval_days
                        )
                    }
                }
            }
        }
    }
}