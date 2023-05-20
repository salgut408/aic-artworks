package com.salvador.artapp.ui.screens.home_screen

import com.salvador.artapp.domain.domain_models.ArtworkModel
import com.salvador.artapp.domain.domain_models.ConfigModel
import com.salvador.artapp.domain.domain_models.PaginationModel

data class ListUiState(
    val currentList: List<ArtworkModel> = listOf(),
    val isLoading: Boolean = true,
    val pagination: PaginationModel = PaginationModel(),
    val config: ConfigModel = ConfigModel(),
val currentPage: Int = 0,
    val totalPages: Int = 0
)