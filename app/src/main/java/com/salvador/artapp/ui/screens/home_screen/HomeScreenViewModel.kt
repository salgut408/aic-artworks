package com.salvador.artapp.ui.screens.home_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salvador.artapp.domain.domain_models.list.ArtworkModel
import com.salvador.artapp.domain.domain_models.list.ConfigModel
import com.salvador.artapp.domain.domain_models.list.PaginationModel
import com.salvador.artapp.domain.domain_models.random_image.RandomImageModel
import com.salvador.artapp.domain.repositories.ArtworkRepository
import com.salvador.artapp.domain.use_cases.GetArtworksUseCase
import com.salvador.artapp.domain.use_cases.GetRandomArtUseCase
import com.salvador.artapp.utils.Constants.Companion.FIELD_TERMS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val artworkRepository: ArtworkRepository,
    private val getArtworksUseCase: GetArtworksUseCase,
    private val getRandomArtUseCase: GetRandomArtUseCase
) : ViewModel() {

    private val _listUiState = MutableStateFlow(ListUiState(isLoading = true))
    val listUiState: StateFlow<ListUiState> = _listUiState.asStateFlow()


    private val _randomArt = MutableStateFlow(listOf<RandomImageModel>())
    val randomArt: StateFlow<List<RandomImageModel>> = _randomArt

    val getAllImages = artworkRepository.getAllImagesModelsRemoteMediator()

//    val art: Flow<PagingData<ArtworkModel>> = Pager(PagingConfig(pageSize = 20)) {
//        ArtSource(getArtworksUseCase)
//    }.flow




    var artPage = 1

    init {
        loadAllArtworks()
    }




    private fun loadAllArtworks() = viewModelScope.launch {

        try {
            val response = getArtworksUseCase(FIELD_TERMS, artPage)
            val artworks = response.artWorks
            val config = response.config
            val pagination = response.pagination
            if (artworks.isNotEmpty()) {
                setListUiState(
                    artworks = _listUiState.value.currentList + artworks,
                    isLoading = false,
                    pagination = pagination,
                    config = config,
                    currentPage = pagination.currentPage,
                    totalPages = pagination.totalPages
                )


                val randomArt = getRandomArtUseCase(1)
                _randomArt.emit(randomArt)

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
        totalPages: Int,
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