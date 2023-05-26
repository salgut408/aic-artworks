package com.salvador.artapp.data.remote.network_responses.random_images


import com.google.gson.annotations.SerializedName

data class RandomImagesNetworkResponse(
//    @SerializedName("config")
//    val config: Config? = Config(),
    @SerializedName("data")
    val randomImages: List<RandomImage?>? = listOf(),
//    @SerializedName("info")
//    val info: Info? = Info(),
//    @SerializedName("pagination")
//    val pagination: Pagination? = Pagination()
)