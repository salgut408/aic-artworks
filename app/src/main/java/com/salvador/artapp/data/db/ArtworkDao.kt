package com.salvador.artapp.data.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.salvador.artapp.data.db.artwork_db.ArtworkDbEntity
import com.salvador.artapp.domain.domain_models.ArtworkModel

interface ArtworkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtworksList(list: List<ArtworkDbEntity>)

    @Delete
    suspend fun delete(artwork: ArtworkDbEntity)
}