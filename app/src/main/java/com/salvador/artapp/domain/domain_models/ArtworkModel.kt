package com.salvador.artapp.domain.domain_models

data class ArtworkModel(
    val artistDisplay: String = "",
    val artistTitle: String = "",
    val classificationTitle: String = "",
    val dateStart: Int = 0,
    val id: Int = 0,
    val imageId: String = "",
    val inscriptions: String = "",
    val latitude: String = "",
    val longitude: String = "",
    val mediumDisplay: String = "",
    val provenanceText: String = "",
    val styleTitle: String = "",
    val title: String = ""
)