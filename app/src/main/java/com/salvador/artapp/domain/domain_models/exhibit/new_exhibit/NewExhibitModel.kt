package com.salvador.artapp.domain.domain_models.exhibit.new_exhibit

import com.google.gson.annotations.SerializedName
import com.salvador.artapp.data.remote.network_responses.exhibitions.new_exhibits.SuggestAutocompleteAll

data class NewExhibitModel(

    val aicEndAt: String? = "",
    val aicStartAt: String? = "",
    val altImageIds: List<String> = listOf(),
    val apiLink: String? = "",
    val apiModel: String? = "",
    val artistIds: List<String> = listOf(),
    val artworkIds: List<String> = listOf(),
    val artworkTitles: List<String> = listOf(),
    val galleryId: Int? = 0,
    val galleryTitle: String? = "",
    val id: String? = "",
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