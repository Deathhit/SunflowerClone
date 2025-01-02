package tw.com.deathhit.core.sunflower_clone_database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import tw.com.deathhit.core.sunflower_clone_database.Column

@Entity(primaryKeys = [Column.PLANT_ID])
data class PlantEntity(
    @ColumnInfo(name = Column.DESCRIPTION) val description: String,
    @ColumnInfo(name = Column.GROW_ZONE_NUMBER) val growZoneNumber: Int,
    @ColumnInfo(name = Column.IMAGE_URL) val imageUrl: String,
    @ColumnInfo(name = Column.PLANT_ID) val plantId: String,
    @ColumnInfo(name = Column.PLANT_NAME) val plantName: String,
    @ColumnInfo(name = Column.WATERING_INTERVAL_DAYS) val wateringIntervalDays: Int
)
