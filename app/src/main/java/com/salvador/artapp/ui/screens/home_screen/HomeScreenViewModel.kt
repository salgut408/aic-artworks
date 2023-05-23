package com.salvador.artapp.ui.screens.home_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.salvador.artapp.data.repository_impls.paged.ArtSource
import com.salvador.artapp.domain.domain_models.list.ArtworkModel
import com.salvador.artapp.domain.domain_models.list.ConfigModel
import com.salvador.artapp.domain.domain_models.list.PaginationModel
import com.salvador.artapp.domain.repositories.ArtworkRepository
import com.salvador.artapp.domain.use_cases.GetArtworksUseCase
import com.salvador.artapp.utils.Constants.Companion.FIELD_TERMS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val artworkRepository: ArtworkRepository,
    private val getArtworksUseCase: GetArtworksUseCase,
) : ViewModel() {
    private val _listUiState = MutableStateFlow(ListUiState(isLoading = true))
    val listUiState: StateFlow<ListUiState> = _listUiState.asStateFlow()

    val art: Flow<PagingData<ArtworkModel>> = Pager(PagingConfig(pageSize = 20)){
        ArtSource(artworkRepository)
    }.flow

    var artPage = 1
    var searchingArtPage = 1

    init {

        loadAllArtworks()
    }

    private fun addAllToDb(art: List<ArtworkModel>) = viewModelScope.launch {
        artworkRepository.saveAllArt(art)
    }



    private fun loadAllArtworks() = viewModelScope.launch {

        try {
            val response = getArtworksUseCase(FIELD_TERMS, artPage)
            val artwork = response.artWorks
            val config = response.config
            val pagination = response.pagination
            if (artwork.isNotEmpty()) {
                addAllToDb(artwork)
                setListUiState(
                    artworks = _listUiState.value.currentList + artwork,
                    isLoading = false,
                    pagination = pagination,
                    config = config,
                    currentPage = pagination.currentPage,
                    totalPages = pagination.totalPages
                )

                Log.e("LISTUI_STATE", _listUiState.value.toString())
            }
        }
        catch (e: Exception) {
            Log.e("VM_LOAD_ERROR", e.stackTraceToString())
        }
    }

    private fun setListUiState(
        artworks: List<ArtworkModel>,
        isLoading: Boolean,
        config: ConfigModel,
        pagination: PaginationModel,
        currentPage: Int,
        totalPages:Int
    ) {
        _listUiState.update {
            it.copy(
                currentList = artworks,
                isLoading = isLoading,
                config = config,
                pagination = pagination,
                currentPage = currentPage,
                totalPages = totalPages
            )
        }
    }


}