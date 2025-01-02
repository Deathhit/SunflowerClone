package tw.com.deathhit.core.sunflower_clone_database.dao

import androidx.room.Dao
import androidx.room.Upsert
import tw.com.deathhit.core.sunflower_clone_database.entity.PhotoRemoteOrderEntity

@Dao
interface PhotoRemoteOrderDao {
    @Upsert
    suspend fun upsert(entities: List<PhotoRemoteOrderEntity>)
}