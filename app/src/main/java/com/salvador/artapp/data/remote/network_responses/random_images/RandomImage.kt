package com.salvador.artapp.data.remote.network_responses.random_images


import com.google.gson.annotations.SerializedName

data class RandomImage(
    @SerializedName("ahash")
    val ahash: Ahash? = Ahash(),
    @SerializedName("alt_text")
    val altText: Any? = Any(),
    @SerializedName("api_link")
    val apiLink: String? = "",
    @SerializedName("api_model")
    val apiModel: String? = "",
    @SerializedName("artwork_ids")
    val artworkIds: List<Int?>? = listOf(),
    @SerializedName("artwork_titles")
    val artworkTitles: List<String?>? = listOf(),
    @SerializedName("color")
    val color: Color? = Color(),
    @SerializedName("colorfulness")
    val colorfulness: Double? = 0.0,
    @SerializedName("content")
    val content: Any? = Any(),
    @SerializedName("content_e_tag")
    val contentETag: String? = "",
    @SerializedName("credit_line")
    val creditLine: Any? = Any(),
    @SerializedName("fingerprint")
    val fingerprint: Fingerprint? = Fingerprint(),
    @SerializedName("height")
    val height: Int? = 0,
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
    @SerializedName("phash")
    val phash: Phash? = Phash(),
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