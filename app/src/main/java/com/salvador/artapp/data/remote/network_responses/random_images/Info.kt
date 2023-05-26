package com.salvador.artapp.data.remote.network_responses.random_images


import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("license_links")
    val licenseLinks: List<String?>? = listOf(),
    @SerializedName("license_text")
    val licenseText: String? = "",
    @SerializedName("version")
    val version: String? = ""
)