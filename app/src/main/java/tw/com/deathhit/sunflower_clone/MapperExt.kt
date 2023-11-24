package tw.com.deathhit.sunflower_clone

import tw.com.deathhit.core.app_database.entity.PlantEntity
import tw.com.deathhit.sunflower_clone.model.PlantJson

internal fun PlantJson.toPlantEntity() = PlantEntity(
    description = description,
    growZoneNumber = growZoneNumber,
    imageUrl = imageUrl,
    plantId = plantId,
    plantName = name,
    wateringIntervalDays = wateringInterval
)