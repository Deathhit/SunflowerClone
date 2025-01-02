package tw.com.deathhit.core.sunflower_clone_database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import tw.com.deathhit.core.sunflower_clone_database.Column

@Entity(primaryKeys = [Column.PHOTO_ID])
data class PhotoEntity(
    @ColumnInfo(name = Column.AUTHOR_ID) val authorId: String,
    @ColumnInfo(name = Column.AUTHOR_NAME) val authorName: String,
    @ColumnInfo(name = Column.IMAGE_URL) val imageUrl: String,
    @ColumnInfo(name = Column.PHOTO_ID) val photoId: String,
    @ColumnInfo(name = Column.PLANT_NAME) val plantName: String
)
