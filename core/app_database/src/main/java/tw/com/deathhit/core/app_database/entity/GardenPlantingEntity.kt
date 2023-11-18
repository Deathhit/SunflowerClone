package tw.com.deathhit.core.app_database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import tw.com.deathhit.core.app_database.Column

@Entity(indices = [Index(Column.PLANT_ID)])
data class GardenPlantingEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Column.GARDEN_PLANTING_ID) val gardenPlantingId: Int = 0,
    @ColumnInfo(name = Column.PLANT_DATE) val plantDate: Long = System.currentTimeMillis(),
    @ColumnInfo(name = Column.PLANT_ID) val plantId: String
)
