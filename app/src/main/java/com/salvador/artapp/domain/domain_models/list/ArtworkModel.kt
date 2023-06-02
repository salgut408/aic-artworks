package com.salvador.artapp.domain.domain_models.list

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.salvador.artapp.data.db.artwork_db.ArtworkDbEntity
import com.salvador.artapp.domain.domain_models.detail.ColorModel
@Entity(tableName = "artworks_model_table")
data class ArtworkModel(
    val artistDisplay: String = "",
    val artistTitle: String = "",
    val classificationTitle: String = "",
    val dateStart: Int = 0,
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val imageId: String = "",
    val inscriptions: String = "",
    val latitude: String = "",
    val longitude: String = "",
    val mediumDisplay: String = "",
    val provenanceText: String = "",
    val styleTitle: String = "",
    val title: String = "",
    val isFavorite: Boolean = false,
    val imgConfigUrl: String = "",
    val colorfullness: Double = 0.0,


    ) {
    fun getOtherImgUrl(): String {
        val urlForDisplay = "https://www.artic.edu/iiif/2/${imageId}/full/843,/0/default.jpg"
        return urlForDisplay
    }
}

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