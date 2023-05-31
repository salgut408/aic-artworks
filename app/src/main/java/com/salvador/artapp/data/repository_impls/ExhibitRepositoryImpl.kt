package com.salvador.artapp.data.repository_impls

import com.salvador.artapp.data.db.ArtworksDatabase
import com.salvador.artapp.data.remote.api.ArtApi
import com.salvador.artapp.data.remote.network_responses.exhibitions.asDomain
import com.salvador.artapp.domain.domain_models.exhibit.ExhibitModel
import com.salvador.artapp.domain.domain_models.exhibit.ExhibitionsResponseModel
import com.salvador.artapp.domain.repositories.ExhibitRepository

class ExhibitRepositoryImpl(
    val artApi: ArtApi,
    val artDatabase: ArtworksDatabase
): ExhibitRepository {
    override suspend fun getExhibitionsResponse(page: Int): List<ExhibitModel> {
        val response = artApi.getExhibitions(page)
        if (response.isSuccessful) {
            return response.body()?.exhibits?.map { it.asDomain() }!!
        }
        return response.body()?.exhibits?.map { it.asDomain() }!!
    }
}