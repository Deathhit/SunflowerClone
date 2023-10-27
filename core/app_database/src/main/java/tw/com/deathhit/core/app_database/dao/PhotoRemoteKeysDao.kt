package tw.com.deathhit.core.app_database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import tw.com.deathhit.core.app_database.Column
import tw.com.deathhit.core.app_database.entity.PhotoRemoteKeysEntity

@Dao
interface PhotoRemoteKeysDao {
    @Query("DELETE FROM PhotoRemoteKeysEntity")
    suspend fun clearTable()

    @Query("SELECT * FROM PhotoRemoteKeysEntity WHERE :photoId = ${Column.PHOTO_ID}")
    suspend fun get(photoId: String): PhotoRemoteKeysEntity?

    @Upsert
    suspend fun upsert(entities: List<PhotoRemoteKeysEntity>)
}