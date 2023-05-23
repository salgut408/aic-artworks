package com.salvador.artapp.ui.screens.artwork_detail

import com.salvador.artapp.domain.domain_models.detail.ArtDetail

data class DetailUiState (
    val isLoading: Boolean = false,
    val art: ArtDetail = ArtDetail()
        )