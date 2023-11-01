package tw.com.deathhit.core.app_database.json

import androidx.annotation.Keep

@Keep
data class PlantJson(
    val plantId: String,
    val name: String,
    val description: String,
    val growZoneNumber: Int,
    val wateringInterval: Int, // how often the plant should be watered, in days
    val imageUrl: String
)