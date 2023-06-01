package com.salvador.artapp.ui.screens.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.salvador.artapp.data.repository_impls.paged.SearchSource
import com.salvador.artapp.domain.domain_models.list.ArtworkModel
import com.salvador.artapp.domain.domain_models.list.ConfigModel
import com.salvador.artapp.domain.domain_models.list.PaginationModel
import com.salvador.artapp.domain.repositories.ArtworkRepository
import com.salvador.artapp.domain.use_cases.SearchArtUseCase
import com.salvador.artapp.utils.Constants.Companion.FIELD_TERMS
import com.salvador.artapp.utils.printToLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val artworkRepository: ArtworkRepository,
    private val searchArtUseCase: SearchArtUseCase,
) : ViewModel() {
    private val _searchUiState = MutableStateFlow(SearchUiState())
    val searchUiState: StateFlow<SearchUiState> = _searchUiState



    val searchQuery by mutableStateOf( "San francisco")

    val art: Flow<PagingData<ArtworkModel>> = Pager(PagingConfig(pageSize = 20)) {
        SearchSource(searchArtUseCase, searchQuery)
    }.flow

    var searchPage = 1

    init {

        searchArtworks(searchQuery)
//        art.printToLog("CATS_?")

    }




     fun searchArtworks(searchQuery: String) = viewModelScope.launch {
        try {
            val response = searchArtUseCase(FIELD_TERMS, searchPage, searchQuery)
            val artworks = response.artWorks
            val config = response.config
            val pagination = response.pagination
            if (artworks.isNotEmpty()) {
                setSearchUiState(
                    artworks = artworks,
                    isLoading = false,
                    pagination = pagination,
                    config = config,
                    currentPage = pagination.currentPage,
                    totalPages = pagination.totalPages
                )
            }

        }
        catch (e: Exception) {
            Log.e("VM_LOAD_ERROR", e.stackTraceToString())

        }
    }


    private fun setSearchUiState(
        artworks: List<ArtworkModel>,
        isLoading: Boolean,
        config: ConfigModel,
        pagination: PaginationModel,
        currentPage: Int,
        totalPages: Int,
    ) {
        _searchUiState.update {
            it.copy(
                currentList = artworks,
                isLoading = isLoading,
                config = config,
                currentPage = currentPage,
                totalPages = totalPages,
                pagination = pagination
            )
        }
    }
}