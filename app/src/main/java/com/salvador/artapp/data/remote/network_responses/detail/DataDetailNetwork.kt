package com.salvador.artapp.data.remote.network_responses.detail


import com.google.gson.annotations.SerializedName
import com.salvador.artapp.domain.domain_models.detail.ArtDetails

data class DataDetailNetwork(
    @SerializedName("artist_display")
    val artistDisplay: String? = "",
    @SerializedName("artist_id")
    val artistId: Int? = 0,
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
    @SerializedName("department_title")
    val departmentTitle: String? = "",
    @SerializedName("dimensions")
    val dimensions: String? = "",
    @SerializedName("gallery_title")
    val galleryTitle: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
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
    val styleTitles: List<String?>? = listOf(),
    @SerializedName("subject_titles")
    val subjectTitles: List<String?>? = listOf(),
    @SerializedName("theme_titles")
    val themeTitles: List<String?>? = listOf(),
    @SerializedName("title")
    val title: String? = "",
)

fun DataDetailNetwork.asDomain(): ArtDetails {
    return ArtDetails(
        artistDisplay = artistDisplay,
        artistId = artistId,
        artistTitle = artistTitle,
        artworkTypeTitle = artworkTypeTitle,
        classificationTitle = classificationTitle,
        color = color?.asDomain(),
        colorfulness = colorfulness,
        creditLine = creditLine,
        departmentTitle = departmentTitle,
        dimensions = dimensions,
        galleryTitle = galleryTitle,
        id = id,
        imageId = imageId,
        isOnView = isOnView,
        latitude = latitude,
        latlon = latlon,
        longitude = longitude,
        mediumDisplay = mediumDisplay,
        placeOfOrigin = placeOfOrigin,
        styleTitle = styleTitle,
        styleTitles = styleTitles,
        subjectTitles = subjectTitles,
        themeTitles = themeTitles,
        title = title,
    )
}