package com.salvador.artapp.domain.use_cases

import com.salvador.artapp.data.db.ArtworksDatabase
import com.salvador.artapp.domain.domain_models.list.ArtResponseModel
import com.salvador.artapp.domain.domain_models.list.ArtworkModel
import com.salvador.artapp.domain.repositories.ArtworkRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetArtworksUseCase @Inject constructor(
    private val artworkRepository: ArtworkRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
//    private val artworksDatabase: ArtworksDatabase
) {
    suspend operator fun invoke(fieldTerms: String, pageNumber: Int): ArtResponseModel =
        withContext(defaultDispatcher){
            val art = artworkRepository.getFullResponse(fieldTerms, pageNumber)
            return@withContext art
        }
}