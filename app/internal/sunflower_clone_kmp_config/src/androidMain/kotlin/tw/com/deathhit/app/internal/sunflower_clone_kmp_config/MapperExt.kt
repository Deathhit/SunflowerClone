package tw.com.deathhit.app.internal.sunflower_clone_kmp_config

import tw.com.deathhit.app.internal.sunflower_clone_kmp_config.model.PlantJson
import tw.com.deathhit.core.sunflowerclonedatabasekmp.PlantEntity

internal fun PlantJson.toPlantEntity() = PlantEntity(
    description = description,
    grow_zone_number = growZoneNumber.toLong(),
    image_url = imageUrl,
    plant_id = plantId,
    plant_name = name,
    watering_interval_days = wateringInterval.toLong()
)