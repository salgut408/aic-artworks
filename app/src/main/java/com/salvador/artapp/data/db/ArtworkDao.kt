package com.salvador.artapp.data.db

import androidx.paging.PagingSource
import androidx.room.*
import com.salvador.artapp.data.db.artwork_db.ArtworkDbEntity
import com.salvador.artapp.domain.domain_models.list.ArtworkModel

@Dao
interface ArtworkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtworksList(list: List<ArtworkDbEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtworksModelList(list: List<ArtworkModel>)

    @Delete
    suspend fun delete(artwork: ArtworkDbEntity)

    @Delete
    suspend fun deleteModel(artwork: ArtworkModel)

    @Query("DELETE FROM artworks_table")
    suspend fun clearAll()

    @Query("DELETE FROM artworks_model_table")
    suspend fun clearAllArtworkModels()


    @Query("SELECT * FROM artworks_table" )
    fun pagingSourceGetAll(): PagingSource<Int, ArtworkDbEntity>

    @Query("SELECT * FROM artworks_model_table" )
    fun pagingSourceGetAllArtWorkModels(): PagingSource<Int, ArtworkModel>


    @Query("SELECT * FROM artworks_table")
    fun getAllArtworks(): List<ArtworkDbEntity>
}