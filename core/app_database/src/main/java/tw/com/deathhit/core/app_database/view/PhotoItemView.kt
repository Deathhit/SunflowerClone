package tw.com.deathhit.core.app_database.view

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import tw.com.deathhit.core.app_database.Column

@DatabaseView(
    "SELECT " +
            "PhotoEntity.${Column.AUTHOR_ID} AS ${Column.AUTHOR_ID}, " +
            "PhotoEntity.${Column.AUTHOR_NAME} AS ${Column.AUTHOR_NAME}, " +
            "PhotoEntity.${Column.IMAGE_URL} AS ${Column.IMAGE_URL}, " +
            "PhotoEntity.${Column.PHOTO_ID} AS ${Column.PHOTO_ID}, " +
            "PhotoEntity.${Column.PLANT_ID} AS ${Column.PLANT_ID}, " +
            "PhotoRemoteOrderEntity.${Column.REMOTE_ORDER} AS ${Column.REMOTE_ORDER} " +
            "FROM PhotoEntity " +
            "LEFT JOIN PhotoRemoteOrderEntity ON PhotoRemoteOrderEntity.${Column.PHOTO_ID} = PhotoEntity.${Column.PHOTO_ID}"
)
data class PhotoItemView(
    @ColumnInfo(name = Column.AUTHOR_ID) val authorId: String,
    @ColumnInfo(name = Column.AUTHOR_NAME) val authorName: String,
    @ColumnInfo(name = Column.IMAGE_URL) val imageUrl: String,
    @ColumnInfo(name = Column.PHOTO_ID) val photoId: String,
    @ColumnInfo(name = Column.PLANT_ID) val plantId: String,
    @ColumnInfo(name = Column.REMOTE_ORDER) val remoteOrder: Int?
)
