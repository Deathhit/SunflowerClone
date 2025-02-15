package tw.com.deathhit.app.internal.sunflower_clone_kmp_config

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import tw.com.deathhit.app.internal.sunflower_clone_kmp_config.SeedDataWorker.Companion.scheduleSeedingDatabase
import javax.inject.Inject

open class SunflowerCloneApplication : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    //todo find another way to properly populate database,
    override fun onCreate() {
        super.onCreate()

        scheduleSeedingDatabase()
    }
}