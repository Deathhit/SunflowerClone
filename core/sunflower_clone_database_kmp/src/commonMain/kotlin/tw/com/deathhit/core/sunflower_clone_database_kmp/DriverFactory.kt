package tw.com.deathhit.core.sunflower_clone_database_kmp

import app.cash.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): SunflowerCloneDatabase {
    val driver = driverFactory.createDriver()

    return SunflowerCloneDatabase(driver)
}