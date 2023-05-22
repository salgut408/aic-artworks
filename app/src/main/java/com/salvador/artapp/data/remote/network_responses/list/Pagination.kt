package com.salvador.artapp.data.remote.network_responses.list


import com.google.gson.annotations.SerializedName
import com.salvador.artapp.domain.domain_models.list.PaginationModel

data class Pagination(
    @SerializedName("current_page")
    val currentPage: Int? = 0,
    @SerializedName("limit")
    val limit: Int? = 0,
    @SerializedName("next_url")
    val nextUrl: String? = "",
    @SerializedName("offset")
    val offset: Int? = 0,
    @SerializedName("total")
    val total: Int? = 0,
    @SerializedName("total_pages")
    val totalPages: Int? = 0
)

fun Pagination.asDomain(): PaginationModel {
    return PaginationModel(
        currentPage = currentPage ?: 0,
        limit = limit ?: 0,
        nextUrl = nextUrl ?: "",
        offset = offset ?: 0,
        total = total ?: 0,
        totalPages = totalPages ?: 0
    )
}