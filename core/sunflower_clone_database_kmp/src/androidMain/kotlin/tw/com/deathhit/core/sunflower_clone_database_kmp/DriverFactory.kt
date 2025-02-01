package tw.com.deathhit.core.sunflower_clone_database_kmp

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

actual class DriverFactory(private val context: Context, private val databaseName: String) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(SunflowerCloneDatabase.Schema, context, databaseName)
    }
}
