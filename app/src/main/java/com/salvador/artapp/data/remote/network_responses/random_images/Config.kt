package com.salvador.artapp.data.remote.network_responses.random_images


import com.google.gson.annotations.SerializedName

data class Config(
    @SerializedName("iiif_url")
    val iiifUrl: String? = "",
    @SerializedName("website_url")
    val websiteUrl: String? = ""
)