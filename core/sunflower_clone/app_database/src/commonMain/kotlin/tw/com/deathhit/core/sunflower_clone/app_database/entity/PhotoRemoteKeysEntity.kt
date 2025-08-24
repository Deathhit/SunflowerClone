package tw.com.deathhit.core.sunflower_clone.app_database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import tw.com.deathhit.core.sunflower_clone.app_database.Column

@Entity(
    primaryKeys = [Column.PHOTO_ID],
    foreignKeys = [ForeignKey(
        childColumns = [Column.PHOTO_ID],
        entity = PhotoEntity::class,
        onDelete = ForeignKey.CASCADE,
        parentColumns = [Column.PHOTO_ID]
    )]
)
data class PhotoRemoteKeysEntity(
    @ColumnInfo(name = Column.NEXT_KEY) val nextKey: Int?,
    @ColumnInfo(name = Column.PHOTO_ID) val photoId: String,
    @ColumnInfo(name = Column.PREVIOUS_KEY) val previousKey: Int?
)
