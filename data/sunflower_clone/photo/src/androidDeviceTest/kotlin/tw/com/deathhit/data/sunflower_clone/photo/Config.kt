package tw.com.deathhit.data.sunflower_clone.photo

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import tw.com.deathhit.core.sunflower_clone.app_database.SunflowerCloneDatabase

object Config {
    fun createAppDatabase() =
        Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SunflowerCloneDatabase::class.java
        ).build()
}