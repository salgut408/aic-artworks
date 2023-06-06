package com.salvador.artapp.ui.screens.exhibits

import com.salvador.artapp.domain.domain_models.exhibit.new_exhibit.ExhibitModel

data class ExhibitScreenUiState(
    val data: List<ExhibitModel> = listOf()
) {
}