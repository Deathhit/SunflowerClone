package tw.com.deathhit.core.app_database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import tw.com.deathhit.core.app_database.Column

@Entity(
    primaryKeys = [Column.PHOTO_ID],
    foreignKeys = [ForeignKey(
        childColumns = [Column.PHOTO_ID],
        entity = PhotoEntity::class,
        onDelete = ForeignKey.CASCADE,
        parentColumns = [Column.PHOTO_ID]
    )]
)
data class PhotoRemoteOrderEntity(
    @ColumnInfo(name = Column.PHOTO_ID) val photoId: String,
    @ColumnInfo(name = Column.REMOTE_ORDER) val remoteOrder: Int
)
