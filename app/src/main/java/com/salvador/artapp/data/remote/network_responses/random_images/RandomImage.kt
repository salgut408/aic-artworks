package com.salvador.artapp.data.remote.network_responses.random_images


import com.google.gson.annotations.SerializedName
import com.salvador.artapp.domain.domain_models.list.ArtworkModel
import com.salvador.artapp.domain.domain_models.random_image.RandomImageModel

data class RandomImage(
    @SerializedName("api_link")
    val apiLink: String? = "",
    @SerializedName("api_model")
    val apiModel: String? = "",
    @SerializedName("color")
    val color: Color? = Color(),
    @SerializedName("colorfulness")
    val colorfulness: Double? = 0.0,
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("iiif_url")
    val iiifUrl: String? = "",
    @SerializedName("is_educational_resource")
    val isEducationalResource: Boolean? = false,
    @SerializedName("is_multimedia_resource")
    val isMultimediaResource: Boolean? = false,
    @SerializedName("is_teacher_resource")
    val isTeacherResource: Boolean? = false,
    @SerializedName("lake_guid")
    val lakeGuid: String? = "",
    @SerializedName("lqip")
    val lqip: String? = "",
    @SerializedName("source_updated_at")
    val sourceUpdatedAt: String? = "",
    @SerializedName("timestamp")
    val timestamp: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("updated_at")
    val updatedAt: String? = "",
    @SerializedName("width")
    val width: Int? = 0
)



fun RandomImage.asDomain(): RandomImageModel {
    return RandomImageModel(
        color = color,
        colorfulness = colorfulness,
        iiifUrl = iiifUrl,
        id = id,
        lakeGuid = lakeGuid,
        title = title,
        type = type,
        width = width
    )
}