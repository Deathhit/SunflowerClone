package tw.com.deathhit.data.plant_kmp

import tw.com.deathhit.core.sunflowerclonedatabasekmp.PlantItemView
import tw.com.deathhit.domain.model.PlantDO

internal fun PlantItemView.toPlantDO() = PlantDO(
    description = description,
    growZoneNumber = grow_zone_number.toInt(),
    imageUrl = image_url,
    plantDate = plant_date,
    plantId = plant_id,
    plantName = plant_name,
    wateringIntervalDays = watering_interval_days.toInt()
)