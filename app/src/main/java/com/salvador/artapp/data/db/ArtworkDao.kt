package com.salvador.artapp.data.db

import androidx.paging.PagingSource
import androidx.room.*
import com.salvador.artapp.domain.domain_models.list.ArtworkModel

@Dao
interface ArtworkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtworksModelList(list: List<ArtworkModel>)

    @Delete
    suspend fun deleteModel(artwork: ArtworkModel)

    @Query("DELETE FROM artworks_model_table")
    suspend fun clearAllArtworkModels()

    @Query("SELECT * FROM artworks_model_table" )
    fun pagingSourceGetAllArtWorkModels(): PagingSource<Int, ArtworkModel>
}