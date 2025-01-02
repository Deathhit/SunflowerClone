package tw.com.deathhit.data.garden_planting

import tw.com.deathhit.core.sunflower_clone_database.view.GardenPlantingItemView
import tw.com.deathhit.domain.model.GardenPlantingDO

internal fun GardenPlantingItemView.toGardenPlantingDO() = GardenPlantingDO(
    description = description,
    gardenPlantingId = gardenPlantingId,
    growZoneNumber = growZoneNumber,
    imageUrl = imageUrl,
    plantDate = plantDate,
    plantId = plantId,
    plantName = plantName,
    wateringIntervalDays = wateringIntervalDays
)