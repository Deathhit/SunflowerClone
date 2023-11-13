package tw.com.deathhit.domain.model

data class PlantDO(
    val description: String,
    val growZoneNumber: Int,
    val imageUrl: String,
    val plantDate: Long?,
    val plantId: String,
    val plantName: String,
    val wateringIntervalDays: Int
) {
    val isPlanted = plantDate != null
}
