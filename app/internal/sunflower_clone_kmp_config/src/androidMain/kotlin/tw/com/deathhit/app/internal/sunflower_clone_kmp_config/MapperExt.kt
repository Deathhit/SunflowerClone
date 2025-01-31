package tw.com.deathhit.app.internal.sunflower_clone_kmp_config

import tw.com.deathhit.app.internal.sunflower_clone_kmp_config.model.PlantJson
import tw.com.deathhit.core.sunflower_clone_database.entity.PlantEntity

internal fun PlantJson.toPlantEntity() = PlantEntity(
    description = description,
    growZoneNumber = growZoneNumber,
    imageUrl = imageUrl,
    plantId = plantId,
    plantName = name,
    wateringIntervalDays = wateringInterval
)