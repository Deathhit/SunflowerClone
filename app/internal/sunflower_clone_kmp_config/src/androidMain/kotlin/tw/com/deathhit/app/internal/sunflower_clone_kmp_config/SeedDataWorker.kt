package tw.com.deathhit.app.internal.sunflower_clone_kmp_config

import android.content.Context
import android.os.Build
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tw.com.deathhit.app.internal.sunflower_clone_kmp_config.model.PlantJson
import tw.com.deathhit.core.sunflower_clone_database_kmp.SunflowerCloneDatabase
import tw.com.deathhit.core.sunflower_clone_database_kmp.plantDao

@HiltWorker
internal class SeedDataWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val sunflowerCloneDatabase: SunflowerCloneDatabase
) : CoroutineWorker(appContext, workerParams) {
    //todo should use a repository method to seed the database instead
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            applicationContext.assets.open("plants_9eabcfec0e4b4af18f213dad403f3e47.json")
                .use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val plantType = object : TypeToken<List<PlantJson>>() {}.type
                        val plantList: List<PlantJson> = Gson().fromJson(jsonReader, plantType)

                        sunflowerCloneDatabase.plantDao().upsert(plantList.map { it.toPlantEntity() })

                        Result.success()
                    }
                }
        } catch (ex: Exception) {
            Result.failure()
        }
    }

    companion object {
        private const val UNIQUE_WORK_NAME = "seed_data_bdda548fc3084f8097df36fb9735565e"

        internal fun Context.scheduleSeedingDatabase() {
            val workManager = WorkManager.getInstance(this)
            val workRequest = OneTimeWorkRequestBuilder<SeedDataWorker>().apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
                    setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            }.build()

            workManager.enqueueUniqueWork(
                UNIQUE_WORK_NAME,
                ExistingWorkPolicy.KEEP,
                workRequest
            )
        }
    }
}
