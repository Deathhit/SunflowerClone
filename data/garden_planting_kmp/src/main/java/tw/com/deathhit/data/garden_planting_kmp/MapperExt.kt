package tw.com.deathhit.data.garden_planting_kmp

import tw.com.deathhit.core.sunflowerclonedatabasekmp.GardenPlantingItemView
import tw.com.deathhit.domain.model.GardenPlantingDO

internal fun GardenPlantingItemView.toGardenPlantingDO() = GardenPlantingDO(
    description = description,
    gardenPlantingId = garden_planting_id.toInt(),
    growZoneNumber = grow_zone_number?.toInt(),
    imageUrl = image_url,
    plantDate = plant_date,
    plantId = plant_id,
    plantName = plant_name,
    wateringIntervalDays = watering_interval_days?.toInt()
)