package com.salvador.artapp.data.repository_impls

import com.salvador.artapp.data.db.ArtworksDatabase
import com.salvador.artapp.data.remote.api.ArtApi
import com.salvador.artapp.data.remote.network_responses.detail.NetworkDetail
import com.salvador.artapp.data.remote.network_responses.detail.asDomain
import com.salvador.artapp.data.remote.network_responses.list.asDomain
import com.salvador.artapp.domain.domain_models.detail.ArtDetail
import com.salvador.artapp.domain.domain_models.list.ArtResponseModel
import com.salvador.artapp.domain.domain_models.list.ArtworkModel
import com.salvador.artapp.domain.domain_models.list.asArtworkDbEntity
import com.salvador.artapp.domain.repositories.ArtworkRepository
import com.salvador.artapp.utils.Constants.Companion.FIELD_TERMS

class ArtworkRepositoryImpl(
    val artApi: ArtApi,
    val artworksDatabase: ArtworksDatabase
): ArtworkRepository {


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

    override suspend fun getArtDetail(
        id: String,
    ): ArtDetail {
       val response = artApi.getArtDetails(id)
        if (response.isSuccessful){
            return response.body()?.asDomain()!!
        }
        return response.body()?.asDomain()!!
    }

    override suspend fun getFullResponse(fieldTerms: String, pageNumber: Int) =
        artApi.getAllArt(FIELD_TERMS, pageNumber).body()?.asDomain()!!




}