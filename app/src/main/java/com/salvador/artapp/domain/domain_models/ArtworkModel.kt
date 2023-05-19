package com.salvador.artapp.domain.domain_models

import com.salvador.artapp.data.db.artwork_db.ArtworkDbEntity

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
    val title: String = "",
    val isFavorite: Boolean = false
)

fun ArtworkModel.asArtworkDbEntity(): ArtworkDbEntity {
    return ArtworkDbEntity(
        artistDisplay = artistDisplay,
        artistTitle = artistTitle,
        classificationTitle = classificationTitle,
        id = id,
        imageId = imageId,
        inscriptions = inscriptions,
        latitude = latitude,
        longitude = longitude,
        mediumDisplay = mediumDisplay,
        provenanceText = provenanceText
    )
}