package com.salvador.artapp.data.db.artwork_db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.salvador.artapp.domain.domain_models.list.ArtworkModel

@Entity(tableName = "artworks_table")
data class ArtworkDbEntity(
    val artistDisplay: String = "",
    val artistTitle: String = "",
    val classificationTitle: String = "",
    @PrimaryKey(autoGenerate = false)
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

fun ArtworkDbEntity.asDomain(): ArtworkModel {
    return  ArtworkModel(
        artistDisplay = artistDisplay,
        artistTitle = artistTitle,
        classificationTitle = classificationTitle,
        dateStart = 0,
        id = id,
        imageId = imageId,
        inscriptions = inscriptions,
        latitude = latitude,
        longitude = longitude,
        provenanceText = provenanceText,
        mediumDisplay = mediumDisplay,
        styleTitle = styleTitle,
        title = title
    )
}