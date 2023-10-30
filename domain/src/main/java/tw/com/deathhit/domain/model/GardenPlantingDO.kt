package tw.com.deathhit.domain.model

data class GardenPlantingDO(
    val description: String?,
    val gardenPlantingId: Int,
    val growZoneNumber: Int?,
    val imageUrl: String?,
    val plantDate: Long,
    val plantId: String,
    val plantName: String?,
    val wateringIntervalDays: Int?
)
