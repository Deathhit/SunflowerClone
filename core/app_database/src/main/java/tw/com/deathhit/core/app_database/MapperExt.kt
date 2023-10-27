package tw.com.deathhit.core.app_database

import tw.com.deathhit.core.app_database.entity.PlantEntity
import tw.com.deathhit.core.app_database.json.PlantJson

internal fun PlantJson.toPlantEntity() = PlantEntity(
    description = description,
    growZoneNumber = growZoneNumber,
    imageUrl = imageUrl,
    plantId = plantId,
    plantName = name,
    wateringIntervalDays = wateringInterval
)