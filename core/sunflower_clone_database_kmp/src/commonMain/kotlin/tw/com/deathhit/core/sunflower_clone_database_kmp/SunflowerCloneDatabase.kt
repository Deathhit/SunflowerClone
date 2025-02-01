package tw.com.deathhit.core.sunflower_clone_database_kmp

import androidx.paging.PagingSource
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOneOrNull
import app.cash.sqldelight.paging3.QueryPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import tw.com.deathhit.core.sunflower_clone_database_kmp.dao.GardenPlantingDao
import tw.com.deathhit.core.sunflower_clone_database_kmp.dao.GardenPlantingItemDao
import tw.com.deathhit.core.sunflower_clone_database_kmp.dao.PhotoDao
import tw.com.deathhit.core.sunflower_clone_database_kmp.dao.PhotoItemDao
import tw.com.deathhit.core.sunflower_clone_database_kmp.dao.PhotoRemoteKeysDao
import tw.com.deathhit.core.sunflower_clone_database_kmp.dao.PhotoRemoteOrderDao
import tw.com.deathhit.core.sunflower_clone_database_kmp.dao.PlantDao
import tw.com.deathhit.core.sunflower_clone_database_kmp.dao.PlantItemDao
import tw.com.deathhit.core.sunflowerclonedatabasekmp.GardenPlantingEntity
import tw.com.deathhit.core.sunflowerclonedatabasekmp.GardenPlantingItemView
import tw.com.deathhit.core.sunflowerclonedatabasekmp.PhotoEntity
import tw.com.deathhit.core.sunflowerclonedatabasekmp.PhotoItemView
import tw.com.deathhit.core.sunflowerclonedatabasekmp.PhotoRemoteKeysEntity
import tw.com.deathhit.core.sunflowerclonedatabasekmp.PhotoRemoteOrderEntity
import tw.com.deathhit.core.sunflowerclonedatabasekmp.PlantEntity
import tw.com.deathhit.core.sunflowerclonedatabasekmp.PlantItemView

fun SunflowerCloneDatabase.gardenPlantingDao(): GardenPlantingDao = object : GardenPlantingDao {
    private val queries = gardenPlantingEntityQueries

    override suspend fun upsert(entities: List<GardenPlantingEntity>) {
        withContext(Dispatchers.IO) {
            transaction {
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

fun SunflowerCloneDatabase.gardenPlantingItemDao(): GardenPlantingItemDao =
    object : GardenPlantingItemDao {
        private val queries = gardenPlantingItemViewQueries

        override fun getEntitiesPagingSource(): PagingSource<Int, GardenPlantingItemView> =
            QueryPagingSource(
                countQuery = queries.count(),
                transacter = queries,
                context = Dispatchers.IO,
                queryProvider = queries::getEntitiesPagingSource
            )
    }

fun SunflowerCloneDatabase.photoDao(): PhotoDao = object : PhotoDao {
    private val queries = photoEntityQueries

    override suspend fun clearByPlantName(plantName: String) {
        withContext(Dispatchers.IO) {
            queries.clearByPlantName(plant_name = plantName)
        }
    }

    override suspend fun upsert(entities: List<PhotoEntity>) {
        withContext(Dispatchers.IO) {
            transaction {
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

fun SunflowerCloneDatabase.photoItemDao(): PhotoItemDao = object : PhotoItemDao {
    private val queries = photoItemViewQueries

    override fun getEntitiesPagingSource(plantName: String): PagingSource<Int, PhotoItemView> =
        QueryPagingSource(
            countQuery = queries.count(),
            transacter = queries,
            context = Dispatchers.IO,
            queryProvider = { limit, offset ->
                queries.getEntitiesPagingSource(
                    plant_name = plantName,
                    limit = limit,
                    offset = offset
                )
            }
        )
}

fun SunflowerCloneDatabase.photoRemoteKeysDao(): PhotoRemoteKeysDao = object : PhotoRemoteKeysDao {
    private val queries = photoRemoteKeysEntityQueries

    override suspend fun get(photoId: String): PhotoRemoteKeysEntity? =
        withContext(Dispatchers.IO) {
            queries.get(photo_id = photoId).executeAsOneOrNull()
        }

    override suspend fun upsert(entities: List<PhotoRemoteKeysEntity>) {
        withContext(Dispatchers.IO) {
            transaction {
                entities.forEach {
                    queries.upsert(
                        photo_id = it.photo_id,
                        next_key = it.next_key,
                        previous_key = it.previous_key
                    )
                }
            }
        }
    }
}

fun SunflowerCloneDatabase.photoRemoteOrderDao(): PhotoRemoteOrderDao =
    object : PhotoRemoteOrderDao {
        private val queries = photoRemoteOrderEntityQueries

        override suspend fun upsert(entities: List<PhotoRemoteOrderEntity>) {
            withContext(Dispatchers.IO) {
                transaction {
                    entities.forEach {
                        queries.upsert(
                            photo_id = it.photo_id,
                            remote_order = it.remote_order
                        )
                    }
                }
            }
        }
    }

fun SunflowerCloneDatabase.plantDao(): PlantDao = object : PlantDao {
    private val queries = plantEntityQueries

    override suspend fun upsert(entities: List<PlantEntity>) {
        withContext(Dispatchers.IO) {
            transaction {
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

fun SunflowerCloneDatabase.plantItemDao(): PlantItemDao = object : PlantItemDao {
    private val queries = plantItemViewQueries

    override fun getEntityFlow(plantId: String): Flow<PlantItemView?> =
        queries.getEntitiesFlow(plantId = plantId).asFlow().mapToOneOrNull(Dispatchers.IO)

    override fun getEntitiesPagingSource(): PagingSource<Int, PlantItemView> =
        QueryPagingSource(
            countQuery = queries.count(),
            transacter = queries,
            context = Dispatchers.IO,
            queryProvider = queries::getEntitiesPagingSource
        )
}