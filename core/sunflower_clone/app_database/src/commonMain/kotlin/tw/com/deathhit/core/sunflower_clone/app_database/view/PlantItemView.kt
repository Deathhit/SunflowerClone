package tw.com.deathhit.core.sunflower_clone.app_database.view

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import tw.com.deathhit.core.sunflower_clone.app_database.Column

@DatabaseView(
    "SELECT " +
            "PlantEntity.${Column.DESCRIPTION} AS ${Column.DESCRIPTION}, " +
            "PlantEntity.${Column.GROW_ZONE_NUMBER} AS ${Column.GROW_ZONE_NUMBER}, " +
            "PlantEntity.${Column.IMAGE_URL} AS ${Column.IMAGE_URL}, " +
            "GardenPlantingEntity.${Column.PLANT_DATE} AS ${Column.PLANT_DATE}, " +
            "PlantEntity.${Column.PLANT_ID} AS ${Column.PLANT_ID}, " +
            "PlantEntity.${Column.PLANT_NAME} AS ${Column.PLANT_NAME}, " +
            "PlantEntity.${Column.WATERING_INTERVAL_DAYS} AS ${Column.WATERING_INTERVAL_DAYS} " +
            "FROM PlantEntity " +
            "LEFT JOIN GardenPlantingEntity ON GardenPlantingEntity.${Column.PLANT_ID} = PlantEntity.${Column.PLANT_ID}"
)
data class PlantItemView(
    @ColumnInfo(name = Column.DESCRIPTION) val description: String,
    @ColumnInfo(name = Column.GROW_ZONE_NUMBER) val growZoneNumber: Int,
    @ColumnInfo(name = Column.IMAGE_URL) val imageUrl: String,
    @ColumnInfo(name = Column.PLANT_DATE) val plantDate: Long?,
    @ColumnInfo(name = Column.PLANT_ID) val plantId: String,
    @ColumnInfo(name = Column.PLANT_NAME) val plantName: String,
    @ColumnInfo(name = Column.WATERING_INTERVAL_DAYS) val wateringIntervalDays: Int
)
