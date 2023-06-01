package com.salvador.artapp.data.remote.network_responses.random_images


import com.google.gson.annotations.SerializedName
import com.salvador.artapp.domain.domain_models.list.InfoModel

data class Info(
    @SerializedName("license_links")
    val licenseLinks: List<String> = listOf(),
    @SerializedName("license_text")
    val licenseText: String? = "",
    @SerializedName("version")
    val version: String? = ""
)

fun Info.asDomain(): InfoModel {
    return InfoModel(
        licenseLinks = licenseLinks,
        licenseText = licenseText ?: "",
        version = version ?: ""

    )
}