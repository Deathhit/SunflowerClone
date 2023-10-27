package tw.com.deathhit.core.app_database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import tw.com.deathhit.core.app_database.Column

@Entity(
    primaryKeys = [Column.PHOTO_ID],
    foreignKeys = [ForeignKey(
        childColumns = [Column.PLANT_ID],
        entity = PlantEntity::class,
        onDelete = ForeignKey.CASCADE,
        parentColumns = [Column.PLANT_ID]
    )],
    indices = [Index(Column.PLANT_ID)]
)
data class PhotoEntity(
    @ColumnInfo(name = Column.AUTHOR_ID) val authorId: String,
    @ColumnInfo(name = Column.AUTHOR_NAME) val authorName: String,
    @ColumnInfo(name = Column.IMAGE_URL) val imageUrl: String,
    @ColumnInfo(name = Column.PHOTO_ID) val photoId: String,
    @ColumnInfo(name = Column.PLANT_ID) val plantId: String
)
