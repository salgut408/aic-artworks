package com.salvador.artapp.domain.domain_models.random_image

import com.salvador.artapp.data.remote.network_responses.random_images.Color

data class RandomImageModel(
    val color: Color? = Color(),
    val colorfulness: Double? = 0.0,
    val id: String? = "",
    val iiifUrl: String? = "",
    val lakeGuid: String? = "",
    val title: String? = "",
    val type: String? = "",
    val width: Int? = 0
) {
    fun getOtherImgUrl(): String {
        val urlForDisplay = "https://www.artic.edu/iiif/2/${id}/full/843,/0/default.jpg"
        return urlForDisplay
    }
}

