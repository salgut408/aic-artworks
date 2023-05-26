package com.salvador.artapp.data.remote.network_responses.random_images


import com.google.gson.annotations.SerializedName

data class Fingerprint(
    @SerializedName("ahash")
    val ahash: String? = "",
    @SerializedName("dhash")
    val dhash: String? = "",
    @SerializedName("phash")
    val phash: String? = "",
    @SerializedName("whash")
    val whash: String? = ""
)