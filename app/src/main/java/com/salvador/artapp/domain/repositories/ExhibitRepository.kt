package com.salvador.artapp.domain.repositories

import androidx.paging.PagingData
import com.salvador.artapp.domain.domain_models.exhibit.new_exhibit.ExhibitModel
import com.salvador.artapp.domain.domain_models.exhibit.new_exhibit.FullExhibitModel
import kotlinx.coroutines.flow.Flow

interface ExhibitRepository {
    suspend fun getFullExhibitionResponse(page: Int): FullExhibitModel
    fun getFullExhibitionsPagingFlow(page: Int): Flow<PagingData<ExhibitModel>>
}