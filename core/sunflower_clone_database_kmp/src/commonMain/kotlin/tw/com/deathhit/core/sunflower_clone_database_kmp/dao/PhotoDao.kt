package tw.com.deathhit.core.sunflower_clone_database_kmp.dao

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tw.com.deathhit.core.sunflower_clone_database_kmp.SunflowerCloneDatabase
import tw.com.deathhit.core.sunflowerclonedatabasekmp.PhotoEntity

interface PhotoDao {
    suspend fun clearByPlantName(plantName: String)

    suspend fun upsert(entities: List<PhotoEntity>)

    class Imp(private val database: SunflowerCloneDatabase): PhotoDao {
        private val queries = database.photoEntityQueries

        override suspend fun clearByPlantName(plantName: String) {
            withContext(Dispatchers.IO) {
                queries.clearByPlantName(plant_name = plantName)
            }
        }

        override suspend fun upsert(entities: List<PhotoEntity>) {
            withContext(Dispatchers.IO) {
                database.transaction {
                    entities.forEach {
                        queries.upsert(
                            photo_id = it.photo_id,
                            author_id = it.author_id,
                            author_name = it.author_name,
                            image_url = it.image_url,
                            plant_name = it.plant_name
                        )
                    }
                }
            }
        }
    }
}