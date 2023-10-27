package tw.com.deathhit.core.app_database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import tw.com.deathhit.core.app_database.Column

@Entity(
    foreignKeys = [ForeignKey(
        childColumns = [Column.PLANT_ID],
        entity = PlantEntity::class,
        onDelete = ForeignKey.CASCADE,
        parentColumns = [Column.PLANT_ID]
    )],
    indices = [Index(Column.PLANT_ID)]
)
data class GardenPlantingEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Column.GARDEN_PLANTING_ID) val gardenPlantingId: Int = 0,
    @ColumnInfo(name = Column.PLANT_DATE) val plantDate: Long = 0L,
    @ColumnInfo(name = Column.PLANT_ID) val plantId: String
)
