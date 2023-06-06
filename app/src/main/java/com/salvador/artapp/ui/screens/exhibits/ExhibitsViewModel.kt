package com.salvador.artapp.ui.screens.exhibits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.salvador.artapp.data.repository_impls.paged.ExhibitionSource
import com.salvador.artapp.domain.domain_models.exhibit.new_exhibit.ExhibitModel
import com.salvador.artapp.domain.repositories.ExhibitRepository
import com.salvador.artapp.domain.use_cases.GetExhibitsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExhibitsViewModel @Inject constructor(
    private val exhibitRepository: ExhibitRepository,
    private val getExhibits: GetExhibitsUseCase
): ViewModel() {

    val _exhibitUiState = MutableStateFlow(ExhibitScreenUiState())
    val exhibititonUiState: StateFlow<ExhibitScreenUiState> = _exhibitUiState

    val exhibits: Flow<PagingData<ExhibitModel>> = Pager(PagingConfig(pageSize = 20)){
        ExhibitionSource(getExhibits)
    }.flow

    val exhibitPage = 1

    init {
        loadExhibits()
    }

    fun loadExhibits() = viewModelScope.launch {
        val response = getExhibits(exhibitPage)
        if(response.exhibits.isNotEmpty()){
            setExhibitUiState(response.exhibits)
        }
    }

    private fun setExhibitUiState(
        exhibits: List<ExhibitModel>
    ) {
        _exhibitUiState.update {
            it.copy(
                data = exhibits
            )
        }
    }

}