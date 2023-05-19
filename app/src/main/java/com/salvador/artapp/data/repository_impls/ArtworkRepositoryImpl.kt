package com.salvador.artapp.data.repository_impls

import com.salvador.artapp.data.db.ArtworksDatabase
import com.salvador.artapp.data.remote.api.ArtApi
import com.salvador.artapp.data.remote.network_responses.asDomain
import com.salvador.artapp.domain.domain_models.ArtworkModel
import com.salvador.artapp.domain.domain_models.asArtworkDbEntity
import com.salvador.artapp.domain.repositories.ArtworkRepository

class ArtworkRepositoryImpl(
    val artApi: ArtApi,
    val artworksDatabase: ArtworksDatabase
): ArtworkRepository {
    override suspend fun getArtworks(fieldTerms: String, pageNumber: Int): List<ArtworkModel> {
       val response = artApi.getAllArt(fieldTerms, pageNumber)
        if(response.isSuccessful ){
            return response.body()?.artwork?.map { it.asDomain() }!!
        }
        return response.body()?.artwork?.map { it.asDomain() }!!
    }

    override suspend fun searchForArtworks(
        fieldTerms: String,
        searchQuery: String,
        pageNumber: Int,
    ): List<ArtworkModel> {
        val art = artApi.searchForArt(fieldTerms, searchQuery, pageNumber)
        if (art.isSuccessful){
            return art.body()?.artwork?.map { it.asDomain() }!!
        }
        return art.body()?.artwork?.map { it.asDomain() }!!

    }

    override suspend fun saveAllArt(art: List<ArtworkModel>) {
        artworksDatabase.getArtworkDao().insertArtworksList(art.map { it.asArtworkDbEntity() })
    }

}