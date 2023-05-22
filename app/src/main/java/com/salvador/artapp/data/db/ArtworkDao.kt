package com.salvador.artapp.data.db

import androidx.room.*
import com.salvador.artapp.data.db.artwork_db.ArtworkDbEntity

@Dao
interface ArtworkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtworksList(list: List<ArtworkDbEntity>)

    @Delete
    suspend fun delete(artwork: ArtworkDbEntity)

    @Query("DELETE FROM artworks_table")
    fun clear()

//    @Query("SELECT * FROM artworks_table WHERE ")

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateInsert(artwork: ArtworkDbEntity)

//    @Query("SELECT * FROM artworks_table")
//    fun getAllArtworks(): List<ArtworkModel>
}