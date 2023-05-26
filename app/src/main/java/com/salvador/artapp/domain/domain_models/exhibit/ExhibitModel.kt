package com.salvador.artapp.domain.domain_models.exhibit

import com.google.gson.annotations.SerializedName

data class ExhibitModel(
    val aicEndAt: String? = "",
    val aicStartAt: String? = "",
    val altImageIds: List<String?>? = listOf(),
    val apiLink: String? = "",
    val apiModel: String? = "",
    val artistIds: List<Int?>? = listOf(),
    val artworkIds: List<Int?>? = listOf(),
    val artworkTitles: List<String?>? = listOf(),
    val documentIds: List<Any?>? = listOf(),
    val galleryId: Int? = 0,
    val galleryTitle: String? = "",
    val id: Int? = 0,
    val imageId: String? = "",
    val imageUrl: String? = "",
    val isFeatured: Boolean? = false,
    val shortDescription: String? = "",
    val siteIds: List<Any?>? = listOf(),
    val sourceUpdatedAt: String? = "",
    val status: String? = "",
    val suggestAutocompleteBoosted: String? = "",
    val timestamp: String? = "",
    val title: String? = "",
    val updatedAt: String? = "",
    val webUrl: String? = ""
)