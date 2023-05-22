package com.salvador.artapp.ui.screens.home_screen

import com.salvador.artapp.domain.domain_models.list.ArtworkModel
import com.salvador.artapp.domain.domain_models.list.ConfigModel
import com.salvador.artapp.domain.domain_models.list.PaginationModel

data class ListUiState(
    val currentList: List<ArtworkModel> = listOf(),
    val isLoading: Boolean = true,
    val pagination: PaginationModel = PaginationModel(),
    val config: ConfigModel = ConfigModel(),
    val currentPage: Int = 0,
    val totalPages: Int = 0
)