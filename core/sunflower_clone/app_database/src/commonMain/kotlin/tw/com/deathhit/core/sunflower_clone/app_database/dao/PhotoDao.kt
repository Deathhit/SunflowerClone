package tw.com.deathhit.core.sunflower_clone.app_database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import tw.com.deathhit.core.sunflower_clone.app_database.Column
import tw.com.deathhit.core.sunflower_clone.app_database.entity.PhotoEntity
import tw.com.deathhit.core.sunflower_clone.app_database.entity.PhotoRemoteKeysEntity
import tw.com.deathhit.core.sunflower_clone.app_database.entity.PhotoRemoteOrderEntity

@Dao
interface PhotoDao {
    @Query("DELETE FROM PhotoEntity")
    suspend fun clear()

    @Query("SELECT * FROM PhotoRemoteKeysEntity WHERE :photoId = ${Column.PHOTO_ID}")
    suspend fun getRemoteKeys(photoId: String): PhotoRemoteKeysEntity?

    @Upsert
    suspend fun upsertPhoto(entities: List<PhotoEntity>)

    @Upsert
    suspend fun upsertRemoteKeys(entities: List<PhotoRemoteKeysEntity>)

    @Upsert
    suspend fun upsertRemoteOrder(entities: List<PhotoRemoteOrderEntity>)

    @Transaction
    suspend fun upsertPhotoPage(
        entities: List<PhotoEntity>,
        page: Int,
        pageSize: Int
    ) {
        val offset = (page - 1) * pageSize
        val nextKey = page + 1
        val previousKey = (page - 1).let { if (it > 0) it else null }

        val keysList = entities.map {
            PhotoRemoteKeysEntity(
                nextKey = nextKey,
                photoId = it.photoId,
                previousKey = previousKey
            )
        }
        val orderList = entities.mapIndexed { index, entity ->
            PhotoRemoteOrderEntity(
                photoId = entity.photoId,
                remoteOrder = index + offset
            )
        }

        //Upsert the master entities.
        upsertPhoto(entities = entities)

        //Upsert the slave entities.
        upsertRemoteKeys(entities = keysList)
        upsertRemoteOrder(entities = orderList)
    }
}