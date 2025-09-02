package tw.com.deathhit.data.sunflower_clone.plant

import tw.com.deathhit.core.sunflower_clone.app_database.view.PlantItemView
import tw.com.deathhit.domain.sunflower_clone.model.PlantDO

internal fun PlantItemView.toPlantDO() = PlantDO(
    description = description,
    growZoneNumber = growZoneNumber,
    imageUrl = imageUrl,
    plantDate = plantDate,
    plantId = plantId,
    plantName = plantName,
    wateringIntervalDays = wateringIntervalDays
)