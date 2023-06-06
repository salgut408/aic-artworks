package com.salvador.artapp.data.repository_impls

import com.salvador.artapp.data.db.ArtworksDatabase
import com.salvador.artapp.data.remote.api.ArtApi
import com.salvador.artapp.data.remote.network_responses.exhibitions.new_exhibits.asDomain
import com.salvador.artapp.domain.domain_models.exhibit.new_exhibit.FullExhibitModel
import com.salvador.artapp.domain.repositories.ExhibitRepository

class ExhibitRepositoryImpl(
    val artApi: ArtApi,
    val artDatabase: ArtworksDatabase
): ExhibitRepository {


    override suspend fun getFullExhibitionResponse(page: Int): FullExhibitModel {
     return  artApi.getExhibitions(page).body()?.asDomain()!!
    }
}