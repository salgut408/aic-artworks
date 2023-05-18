package com.salvador.artapp.domain.domain_models

data class DataModel(
    val artist_display: String = "",
    val artist_title: String = "",
    val classification_title: String = "",
    val date_start: Int = 0,
    val id: Int = 0,
    val image_id: String = "",
    val inscriptions: String = "",
    val latitude: AnyModel = AnyModel(),
    val longitude: AnyModel = AnyModel(),
    val medium_display: String = "",
    val provenance_text: AnyModel = AnyModel(),
    val style_title: String = "",
    val title: String = ""
)