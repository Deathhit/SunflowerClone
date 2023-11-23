package tw.com.deathhit.core.app_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import tw.com.deathhit.core.app_database.SeedDatabaseWorker.Companion.scheduleSeedingDatabase
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

    companion object {
        private const val FILE_NAME = "app_database_3f86b669755d4f27a1613b339bd87def"

        fun Context.buildAppDatabase() = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            FILE_NAME
        ).addCallback(
            object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    scheduleSeedingDatabase()
                }
            }
        ).build()
    }
}