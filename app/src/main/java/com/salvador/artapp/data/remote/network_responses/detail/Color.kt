package com.salvador.artapp.data.remote.network_responses.detail


import com.google.gson.annotations.SerializedName
import com.salvador.artapp.domain.domain_models.detail.ColorModel

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

fun Color.asDomain(): ColorModel {
    return ColorModel(
        h = h,
        l = l,
        percentage = percentage,
        population = population,
        s = s
    )
}