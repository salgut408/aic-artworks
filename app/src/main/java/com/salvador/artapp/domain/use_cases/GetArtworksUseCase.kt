package com.salvador.artapp.domain.use_cases

import com.salvador.artapp.domain.domain_models.ArtworkModel
import com.salvador.artapp.domain.repositories.ArtworkRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetArtworksUseCase @Inject constructor(
    private val artworkRepository: ArtworkRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    suspend operator fun invoke(fieldTerms: String, pageNumber: Int): List<ArtworkModel> =
        withContext(defaultDispatcher){
            val art = artworkRepository.getArtworks(fieldTerms, pageNumber)
            return@withContext art
        }
}