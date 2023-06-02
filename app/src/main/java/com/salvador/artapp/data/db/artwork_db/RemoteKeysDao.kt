package com.salvador.artapp.data.db.artwork_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.salvador.artapp.data.db.ArtRemoteKey

@Dao
interface RemoteKeysDao {

    @Query("SELECT * FROM remote_keys_table WHERE id = :id")
    suspend fun getRemoteKeys(id: String): ArtRemoteKey

    @Query("DELETE FROM remote_keys_table")
    suspend fun deleteAllRemoteKeys()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<ArtRemoteKey>)
}