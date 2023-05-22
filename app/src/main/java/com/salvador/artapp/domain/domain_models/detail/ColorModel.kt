package com.salvador.artapp.domain.domain_models.detail

import com.google.gson.annotations.SerializedName

data class ColorModel(
    val h: Int? = 0,
    val l: Int? = 0,
    val percentage: Double? = 0.0,
    val population: Int? = 0,
    val s: Int? = 0
)
