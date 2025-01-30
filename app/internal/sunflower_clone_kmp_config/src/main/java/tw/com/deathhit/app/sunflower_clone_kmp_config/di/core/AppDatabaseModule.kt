package tw.com.deathhit.app.sunflower_clone_kmp_config.di.core

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tw.com.deathhit.app.sunflower_clone_kmp_config.SeedDataWorker.Companion.scheduleSeedingDatabase
import tw.com.deathhit.core.sunflower_clone_database.SunflowerCloneDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {
    //todo Replace with the KMP variant
    @Provides
    @Singleton
    internal fun provideAppDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        SunflowerCloneDatabase::class.java,
        "app_database_3f86b669755d4f27a1613b339bd87def"
    ).addCallback(
        object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                context.scheduleSeedingDatabase()
            }
        }
    ).build()
}