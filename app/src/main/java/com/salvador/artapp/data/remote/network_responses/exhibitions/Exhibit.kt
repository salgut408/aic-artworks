package com.salvador.artapp.data.remote.network_responses.exhibitions


import com.google.gson.annotations.SerializedName

data class Exhibit(
    @SerializedName("aic_end_at")
    val aicEndAt: String? = "",
    @SerializedName("aic_start_at")
    val aicStartAt: String? = "",
    @SerializedName("alt_image_ids")
    val altImageIds: List<String?>? = listOf(),
    @SerializedName("api_link")
    val apiLink: String? = "",
    @SerializedName("api_model")
    val apiModel: String? = "",
    @SerializedName("artist_ids")
    val artistIds: List<Int?>? = listOf(),
    @SerializedName("artwork_ids")
    val artworkIds: List<Int?>? = listOf(),
    @SerializedName("artwork_titles")
    val artworkTitles: List<String?>? = listOf(),
    @SerializedName("document_ids")
    val documentIds: List<Any?>? = listOf(),
    @SerializedName("gallery_id")
    val galleryId: Int? = 0,
    @SerializedName("gallery_title")
    val galleryTitle: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("image_id")
    val imageId: String? = "",
    @SerializedName("image_url")
    val imageUrl: String? = "",
    @SerializedName("is_featured")
    val isFeatured: Boolean? = false,
    @SerializedName("short_description")
    val shortDescription: String? = "",
    @SerializedName("site_ids")
    val siteIds: List<Any?>? = listOf(),
    @SerializedName("source_updated_at")
    val sourceUpdatedAt: String? = "",
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("suggest_autocomplete_boosted")
    val suggestAutocompleteBoosted: String? = "",
    @SerializedName("timestamp")
    val timestamp: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("updated_at")
    val updatedAt: String? = "",
    @SerializedName("web_url")
    val webUrl: String? = ""
)
