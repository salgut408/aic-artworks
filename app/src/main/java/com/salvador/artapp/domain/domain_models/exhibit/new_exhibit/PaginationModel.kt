package com.salvador.artapp.domain.domain_models.exhibit.new_exhibit

import com.google.gson.annotations.SerializedName

data class PaginationModel(
    val currentPage: Int? = 0,
    val limit: Int? = 0,
    val nextUrl: String? = "",
    val offset: Int? = 0,
    val total: Int? = 0,
    val totalPages: Int? = 0
)