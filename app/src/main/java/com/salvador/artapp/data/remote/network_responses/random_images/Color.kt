package com.salvador.artapp.data.remote.network_responses.random_images


import com.google.gson.annotations.SerializedName

data class Color(
    @SerializedName("h")
    val h: Int? = 0,
    @SerializedName("l")
    val l: Int? = 0,
    @SerializedName("percentage")
    val percentage: Double? = 0.0,
    @SerializedName("population")
    val population: Int? = 0,
    @SerializedName("s")
    val s: Int? = 0
)