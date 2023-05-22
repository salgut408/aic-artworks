package com.salvador.artapp.domain.domain_models.detail

import com.google.gson.annotations.SerializedName
import com.salvador.artapp.data.remote.network_responses.detail.Color

data class ArtDetails(

    val artistDisplay: String? = "",
    val artistId: Int? = 0,
    val artistTitle: String? = "",
    val provenanceText: String? = "",
    val artworkTypeTitle: String? = "",
    val classificationTitle: String? = "",
    val color: ColorModel? = ColorModel(),
    val colorfulness: Double? = 0.0,
    val creditLine: String? = "",
    val departmentTitle: String? = "",
    val dimensions: String? = "",
    val galleryTitle: String? = "",
    val id: Int? = 0,
    val imageId: String? = "",
    val isOnView: Boolean? = false,
    val latitude: Double? = 0.0,
    val latlon: String? = "",
    val longitude: Double? = 0.0,
    val mediumDisplay: String? = "",
    val placeOfOrigin: String? = "",
    val styleTitle: String? = "",
    val styleTitles: List<String?>? = listOf(),
    val subjectTitles: List<String?>? = listOf(),
    val themeTitles: List<String?>? = listOf(),
    val title: String? = "",
) {
    fun getOtherImgUrl(): String {
        val urlForDisplay = "https://www.artic.edu/iiif/2/${imageId}/full/843,/0/default.jpg"
        return urlForDisplay
    }
}
