package com.salvador.artapp.domain.use_cases

import com.salvador.artapp.domain.domain_models.exhibit.ExhibitModel
import com.salvador.artapp.domain.repositories.ExhibitRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetExhibitsUseCase @Inject constructor(
    private val exhibitRepository: ExhibitRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun  invoke (page: Int): List<ExhibitModel> =
        withContext(defaultDispatcher){
            val exhibits = exhibitRepository.getExhibitionsResponse(page)
            return@withContext exhibits
        }
}