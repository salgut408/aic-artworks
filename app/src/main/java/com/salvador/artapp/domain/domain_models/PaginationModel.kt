package com.salvador.artapp.domain.domain_models

data class PaginationModel(
    val current_page: Int = 0,
    val limit: Int = 0,
    val next_url: String = "",
    val offset: Int = 0,
    val total: Int = 0,
    val total_pages: Int = 0
)