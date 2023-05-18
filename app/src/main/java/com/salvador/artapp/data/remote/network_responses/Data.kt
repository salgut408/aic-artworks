package com.salvador.artapp.data.remote.network_responses


import com.google.gson.annotations.SerializedName
import com.salvador.artapp.domain.domain_models.ArtworkModel

data class Data(
    @SerializedName("artist_display")
    val artistDisplay: String? = "",
    @SerializedName("artist_title")
    val artistTitle: String? = "",
    @SerializedName("classification_title")
    val classificationTitle: String? = "",
    @SerializedName("date_start")
    val dateStart: Int? = 0,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("image_id")
    val imageId: String? = "",
    @SerializedName("inscriptions")
    val inscriptions: String? = "",
    @SerializedName("latitude")
    val latitude: String? = "",
    @SerializedName("longitude")
    val longitude: String? = "",
    @SerializedName("medium_display")
    val mediumDisplay: String? = "",
    @SerializedName("provenance_text")
    val provenanceText: String? = "",
    @SerializedName("style_title")
    val styleTitle: String? = "",
    @SerializedName("title")
    val title: String? = ""
)

fun Data.asDomain(): ArtworkModel {
    return ArtworkModel(
        artistDisplay = artistDisplay ?: "",
        artistTitle = artistTitle ?: "",
        classificationTitle = classificationTitle ?: "",
        dateStart = dateStart ?: 0,
        id = id ?: 0,
        imageId = imageId ?: "",
        inscriptions = inscriptions ?: "",
        latitude = latitude ?: "",
        longitude = longitude ?: "",
        mediumDisplay = mediumDisplay ?: "",
        provenanceText = provenanceText ?: "",
        styleTitle = styleTitle ?: "",
        title = title ?: "",


    )
}