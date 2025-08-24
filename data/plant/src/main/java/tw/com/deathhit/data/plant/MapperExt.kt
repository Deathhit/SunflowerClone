package tw.com.deathhit.data.plant

import tw.com.deathhit.core.sunflower_clone.app_database.view.PlantItemView
import tw.com.deathhit.domain.model.PlantDO

internal fun PlantItemView.toPlantDO() = PlantDO(
    description = description,
    growZoneNumber = growZoneNumber,
    imageUrl = imageUrl,
    plantDate = plantDate,
    plantId = plantId,
    plantName = plantName,
    wateringIntervalDays = wateringIntervalDays
)