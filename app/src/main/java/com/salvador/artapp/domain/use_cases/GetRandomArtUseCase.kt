package com.salvador.artapp.domain.use_cases

import com.salvador.artapp.domain.domain_models.random_image.RandomImageModel
import com.salvador.artapp.domain.repositories.ArtworkRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRandomArtUseCase @Inject constructor(
    private val artworkRepository: ArtworkRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(pageNumber: Int): List<RandomImageModel> =
        withContext(defaultDispatcher) {
            val artList = artworkRepository.getRandomImages(pageNumber)
            return@withContext artList
        }


}