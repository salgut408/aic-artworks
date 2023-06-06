package com.salvador.artapp.data.remote.network_responses.exhibitions.new_exhibits


import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("license_links")
    val licenseLinks: List<String?>? = listOf(),
    @SerializedName("license_text")
    val licenseText: String? = "",
    @SerializedName("version")
    val version: String? = ""
)