package tw.com.deathhit.core.sunflower_clone_database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import tw.com.deathhit.core.sunflower_clone_database.Column
import tw.com.deathhit.core.sunflower_clone_database.entity.PhotoRemoteKeysEntity

@Dao
interface PhotoRemoteKeysDao {
    @Query("SELECT * FROM PhotoRemoteKeysEntity WHERE :photoId = ${Column.PHOTO_ID}")
    suspend fun get(photoId: String): PhotoRemoteKeysEntity?

    @Upsert
    suspend fun upsert(entities: List<PhotoRemoteKeysEntity>)
}