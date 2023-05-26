package com.salvador.artapp.domain.repositories

import com.salvador.artapp.domain.domain_models.exhibit.ExhibitModel
import com.salvador.artapp.domain.domain_models.exhibit.ExhibitionsResponseModel
import com.salvador.artapp.domain.domain_models.list.ArtResponseModel

interface ExhibitRepository {
    suspend fun getExhibitionsResponse(page: Int): List<ExhibitModel>

}