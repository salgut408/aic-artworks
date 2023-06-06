package com.salvador.artapp.domain.repositories

import com.salvador.artapp.domain.domain_models.exhibit.new_exhibit.FullExhibitModel

interface ExhibitRepository {
    suspend fun getFullExhibitionResponse(page:Int): FullExhibitModel

}