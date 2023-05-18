package com.salvador.artapp.data.remote.network_responses


import com.google.gson.annotations.SerializedName

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
    val latitude: Any? = Any(),
    @SerializedName("longitude")
    val longitude: Any? = Any(),
    @SerializedName("medium_display")
    val mediumDisplay: String? = "",
    @SerializedName("provenance_text")
    val provenanceText: Any? = Any(),
    @SerializedName("style_title")
    val styleTitle: String? = "",
    @SerializedName("title")
    val title: String? = ""
)