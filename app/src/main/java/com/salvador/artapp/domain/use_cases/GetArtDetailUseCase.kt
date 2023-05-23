package com.salvador.artapp.domain.use_cases

import com.salvador.artapp.domain.domain_models.detail.ArtDetail
import com.salvador.artapp.domain.domain_models.list.ArtResponseModel
import com.salvador.artapp.domain.repositories.ArtworkRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetArtDetailUseCase @Inject constructor(
    private val artworkRepository: ArtworkRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(id: String): ArtDetail =
        withContext(defaultDispatcher) {
            val artwork = artworkRepository.getArtDetail(id)
            return@withContext artwork
        }

}