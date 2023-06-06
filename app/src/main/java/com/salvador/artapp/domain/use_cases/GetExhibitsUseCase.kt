package com.salvador.artapp.domain.use_cases

import com.salvador.artapp.domain.domain_models.exhibit.new_exhibit.FullExhibitModel
import com.salvador.artapp.domain.repositories.ExhibitRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetExhibitsUseCase @Inject constructor(
    private val exhibitRepository: ExhibitRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun  invoke (page: Int): FullExhibitModel =
        withContext(defaultDispatcher){
            val exhibits = exhibitRepository.getFullExhibitionResponse(page)
            return@withContext exhibits
        }
}