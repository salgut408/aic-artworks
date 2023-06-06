package com.salvador.artapp.data.remote.network_responses.exhibitions.new_exhibits


import com.google.gson.annotations.SerializedName
import com.salvador.artapp.domain.domain_models.exhibit.new_exhibit.ConfigModel

data class Config(
    @SerializedName("iiif_url")
    val iiifUrl: String? = "",
    @SerializedName("website_url")
    val websiteUrl: String? = ""
)
fun Config.asDomain(): ConfigModel {
    return ConfigModel(
        iiif_url = iiifUrl,
        website_url = websiteUrl
    )
}