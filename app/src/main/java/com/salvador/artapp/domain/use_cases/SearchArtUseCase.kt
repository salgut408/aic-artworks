package com.salvador.artapp.domain.use_cases

import com.salvador.artapp.domain.domain_models.list.ArtResponseModel
import com.salvador.artapp.domain.repositories.ArtworkRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchArtUseCase @Inject constructor(
    private val artworkRepository: ArtworkRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,

) {
    suspend operator fun invoke(fieldTerms: String, pageNumber: Int, searchQuery: String): ArtResponseModel =
        withContext(defaultDispatcher) {
            val art = artworkRepository.searchForArtworks(fieldTerms, searchQuery, pageNumber, )
            artworkRepository.saveAllArt(art.artWorks)
            return@withContext art
        }
}