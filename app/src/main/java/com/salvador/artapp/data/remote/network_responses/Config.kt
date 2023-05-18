package com.salvador.artapp.data.remote.network_responses


import com.google.gson.annotations.SerializedName
import com.salvador.artapp.domain.domain_models.ConfigModel

data class Config(
    @SerializedName("iiif_url")
    val iiifUrl: String? = "",
    @SerializedName("website_url")
    val websiteUrl: String? = ""
)

fun Config.asDomain(): ConfigModel {
    return ConfigModel(
        iiif_url = iiifUrl ?: "",
        website_url = websiteUrl ?: ""

    )
}