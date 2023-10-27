package tw.com.deathhit.core.app_database.view

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import tw.com.deathhit.core.app_database.Column

@DatabaseView(
    "SELECT " +
            "PlantEntity.${Column.DESCRIPTION} AS ${Column.DESCRIPTION}, " +
            "GardenPlantingEntity.${Column.GARDEN_PLANTING_ID} AS ${Column.GARDEN_PLANTING_ID}, " +
            "PlantEntity.${Column.GROW_ZONE_NUMBER} AS ${Column.GROW_ZONE_NUMBER}, " +
            "PlantEntity.${Column.IMAGE_URL} AS ${Column.IMAGE_URL}, " +
            "GardenPlantingEntity.${Column.PLANT_DATE} AS ${Column.PLANT_DATE}, " +
            "GardenPlantingEntity.${Column.PLANT_ID} AS ${Column.PLANT_ID}, " +
            "PlantEntity.${Column.PLANT_NAME} AS ${Column.PLANT_NAME}, " +
            "PlantEntity.${Column.WATERING_INTERVAL_DAYS} AS ${Column.WATERING_INTERVAL_DAYS} " +
            "FROM GardenPlantingEntity " +
            "LEFT JOIN PlantEntity ON PlantEntity.${Column.PLANT_ID} = GardenPlantingEntity.${Column.PLANT_ID}"
)
data class GardenPlantingItemView(
    @ColumnInfo(name = Column.DESCRIPTION) val description: String?,
    @ColumnInfo(name = Column.GARDEN_PLANTING_ID) val gardenPlantingId: Int,
    @ColumnInfo(name = Column.GROW_ZONE_NUMBER) val growZoneNumber: Int?,
    @ColumnInfo(name = Column.IMAGE_URL) val imageUrl: String?,
    @ColumnInfo(name = Column.PLANT_DATE) val plantDate: Long,
    @ColumnInfo(name = Column.PLANT_ID) val plantId: String,
    @ColumnInfo(name = Column.PLANT_NAME) val plantName: String?,
    @ColumnInfo(name = Column.WATERING_INTERVAL_DAYS) val wateringIntervalDays: Int?
)
