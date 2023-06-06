package com.salvador.artapp.data.remote.network_responses.exhibitions.new_exhibits


import com.google.gson.annotations.SerializedName
import com.salvador.artapp.domain.domain_models.exhibit.new_exhibit.PaginationModel

data class Pagination(
    @SerializedName("current_page")
    val currentPage: Int = 0,
    @SerializedName("limit")
    val limit: Int = 0,
    @SerializedName("next_url")
    val nextUrl: String = "",
    @SerializedName("offset")
    val offset: Int = 0,
    @SerializedName("total")
    val total: Int = 0,
    @SerializedName("total_pages")
    val totalPages: Int = 0
)
fun Pagination.asDomain(): PaginationModel {
    return PaginationModel(
        currentPage = currentPage,
        limit = limit,
        nextUrl = nextUrl,
        offset = offset,
        total = total,
        totalPages = totalPages
    )
}
