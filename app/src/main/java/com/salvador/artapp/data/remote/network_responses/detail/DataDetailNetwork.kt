package com.salvador.artapp.data.remote.network_responses.detail


import com.google.gson.annotations.SerializedName
import com.salvador.artapp.domain.domain_models.detail.ArtDetails
import com.salvador.artapp.domain.domain_models.detail.ColorModel

data class DataDetailNetwork(
    @SerializedName("artist_display")
    val artistDisplay: String? = "",
    @SerializedName("artist_id")
    val artistId: String? = "0",
    @SerializedName("artist_title")
    val artistTitle: String? = "",
    @SerializedName("artwork_type_title")
    val artworkTypeTitle: String? = "",
    @SerializedName("classification_title")
    val classificationTitle: String? = "",
    @SerializedName("color")
    val color: Color? = Color(),
    @SerializedName("colorfulness")
    val colorfulness: Double? = 0.0,
    @SerializedName("credit_line")
    val creditLine: String? = "",
    @SerializedName("provenance_text")
    val provenanceText: String? = "",
    @SerializedName("department_title")
    val departmentTitle: String? = "",
    @SerializedName("dimensions")
    val dimensions: String? = "",
    @SerializedName("gallery_title")
    val galleryTitle: String? = "",
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("image_id")
    val imageId: String? = "",
    @SerializedName("is_on_view")
    val isOnView: Boolean? = false,
    @SerializedName("latitude")
    val latitude: Double? = 0.0,
    @SerializedName("latlon")
    val latlon: String? = "",
    @SerializedName("longitude")
    val longitude: Double? = 0.0,
    @SerializedName("medium_display")
    val mediumDisplay: String? = "",
    @SerializedName("place_of_origin")
    val placeOfOrigin: String? = "",
    @SerializedName("style_title")
    val styleTitle: String? = "",
    @SerializedName("style_titles")
    val styleTitles: List<String>? = listOf(),
    @SerializedName("subject_titles")
    val subjectTitles: List<String>? = listOf(),
    @SerializedName("theme_titles")
    val themeTitles: List<String>? = listOf(),
    @SerializedName("title")
    val title: String? = "",
)

fun DataDetailNetwork.asDomain(): ArtDetails {
    return ArtDetails(
        artistDisplay = artistDisplay ?: "",
        artistId = artistId ?: "",
        artistTitle = artistTitle ?: "",
        artworkTypeTitle = artworkTypeTitle ?: "",
        classificationTitle = classificationTitle ?: "",
        color = color?.asDomain() ?: ColorModel(),
        colorfulness = colorfulness ?: 0.0,
        creditLine = creditLine ?: "",
        departmentTitle = departmentTitle ?: "",
        dimensions = dimensions ?: "",
        galleryTitle = galleryTitle ?: "",
        id = id ?: "",
        imageId = imageId ?: "",
        isOnView = isOnView ?: false,
        latitude = latitude ?: 0.0,
        latlon = latlon ?: "",
        longitude = longitude ?: 0.0,
        mediumDisplay = mediumDisplay ?: "",
        placeOfOrigin = placeOfOrigin ?: "",
        styleTitle = styleTitle ?: "",
        styleTitles = styleTitles ?: listOf(),
        subjectTitles = subjectTitles?: listOf(),
        themeTitles = themeTitles ?: listOf(),
        title = title ?: "",
        provenanceText = provenanceText ?: ""
    )
}