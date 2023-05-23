package com.salvador.artapp.ui.screens.artwork_detail

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import coil.imageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.salvador.artapp.domain.domain_models.detail.ArtDetail
import com.salvador.artapp.domain.repositories.ArtworkRepository
import com.salvador.artapp.domain.use_cases.GetArtDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getArtDetail: GetArtDetailUseCase,
    private val artworkRepository: ArtworkRepository,
) : ViewModel() {

    private val _detailUiState = MutableStateFlow(DetailUiState())
    val detailUiState: StateFlow<DetailUiState> = _detailUiState.asStateFlow()



    init {

    }

    fun fetchColors(url: String, context: Context, onCalculated: (Color)->Unit) {
        viewModelScope.launch {
            val req = ImageRequest.Builder(context)
                .data(url)
                .allowHardware(false)
                .build()
            val result = req.context.imageLoader.execute(req)
            if (result is SuccessResult) {
                calcDominantColor(result.drawable) { color ->
                    onCalculated(color)
                }
            }
        }
    }


    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
        Palette.from(bmp).generate() { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }

    fun loadArtwork(id: String) = viewModelScope.launch {
        val art = getArtDetail(id)
        Log.e("ART_DETAIL", art.artData.toString())
        setUiState(
            art = art,
            isLoading = false
        )
    }

    private fun setUiState(
        art: ArtDetail,
        isLoading: Boolean,
    ) {
        _detailUiState.update {
            it.copy(
                art = art,
                isLoading = false
            )
        }

    }
}