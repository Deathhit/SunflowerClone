package tw.com.deathhit.core.app_database

import android.content.Context

internal fun Context.openPlantsFile() = assets.open("plants_9eabcfec0e4b4af18f213dad403f3e47.json")