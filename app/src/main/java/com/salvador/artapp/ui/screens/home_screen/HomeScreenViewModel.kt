package com.salvador.artapp.ui.screens.home_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.salvador.artapp.domain.domain_models.ArtworkModel
import com.salvador.artapp.domain.domain_models.ConfigModel
import com.salvador.artapp.domain.domain_models.PaginationModel
import com.salvador.artapp.domain.domain_models.asArtworkDbEntity
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
    private val getArtworksUseCase: GetArtworksUseCase
): ViewModel() {
    private val _listUiState = MutableStateFlow(ListUiState(isLoading = true,))
    val listUiState: StateFlow<ListUiState> = _listUiState.asStateFlow()



     var artPage = 1
     var searchingArtPage = 1

    init {

        loadAllArtworks()
    }

    private fun addAllToDb(art: List<ArtworkModel>) = viewModelScope.launch {
        artworkRepository.saveAllArt(art)
    }



    private fun loadAllArtworks() = viewModelScope.launch{

        try {
            val response = artworkRepository.getFullResponse(FIELD_TERMS, artPage)
            val artwork = response.artWork
            val config = response.config
            val pagination = response.pagination
            if (artwork.isNotEmpty()) {
                addAllToDb(artwork)
                setListUiState(
                    artworks = artwork,
                    isLoading = false,
                    pagination = pagination,
                    config = config
                )
                artPage++ // ??
                Log.e("PAGE???", artPage.toString())

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
        pagination: PaginationModel
    ) {
        _listUiState.update {
            it.copy(
                currentList = artworks,
                isLoading = isLoading,
                config = config,
                pagination = pagination

            )
        }
    }

}