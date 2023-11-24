package tw.com.deathhit.core.app_database

import androidx.room.Database
import androidx.room.RoomDatabase
import tw.com.deathhit.core.app_database.dao.GardenPlantingDao
import tw.com.deathhit.core.app_database.dao.GardenPlantingItemDao
import tw.com.deathhit.core.app_database.dao.PhotoDao
import tw.com.deathhit.core.app_database.dao.PhotoItemDao
import tw.com.deathhit.core.app_database.dao.PhotoRemoteKeysDao
import tw.com.deathhit.core.app_database.dao.PhotoRemoteOrderDao
import tw.com.deathhit.core.app_database.dao.PlantDao
import tw.com.deathhit.core.app_database.dao.PlantItemDao
import tw.com.deathhit.core.app_database.entity.GardenPlantingEntity
import tw.com.deathhit.core.app_database.entity.PhotoEntity
import tw.com.deathhit.core.app_database.entity.PhotoRemoteKeysEntity
import tw.com.deathhit.core.app_database.entity.PhotoRemoteOrderEntity
import tw.com.deathhit.core.app_database.entity.PlantEntity
import tw.com.deathhit.core.app_database.view.GardenPlantingItemView
import tw.com.deathhit.core.app_database.view.PhotoItemView
import tw.com.deathhit.core.app_database.view.PlantItemView

@Database(
    entities = [
        GardenPlantingEntity::class,
        PhotoEntity::class,
        PhotoRemoteKeysEntity::class,
        PhotoRemoteOrderEntity::class,
        PlantEntity::class
    ],
    version = 1,
    views = [
        GardenPlantingItemView::class,
        PhotoItemView::class,
        PlantItemView::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gardenPlantingDao(): GardenPlantingDao
    abstract fun gardenPlantingItemDao(): GardenPlantingItemDao
    abstract fun photoDao(): PhotoDao
    abstract fun photoItemDao(): PhotoItemDao
    abstract fun photoRemoteKeysDao(): PhotoRemoteKeysDao
    abstract fun photoRemoteOrderDao(): PhotoRemoteOrderDao
    abstract fun plantDao(): PlantDao
    abstract fun plantItemDao(): PlantItemDao
}