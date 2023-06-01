package com.salvador.artapp.data.remote.network_responses.random_images


import com.google.gson.annotations.SerializedName
import com.salvador.artapp.domain.domain_models.list.ArtResponseModel
import com.salvador.artapp.domain.domain_models.random_image.RandomImageModel

data class RandomImagesNetworkResponse(
    @SerializedName("config")
    val config: Config? = Config(),
    @SerializedName("data")
    val randomImages: List<RandomImage> = listOf(),
    @SerializedName("info")
    val info: Info? = Info(),
    @SerializedName("pagination")
    val pagination: Pagination? = Pagination()
)

fun RandomImagesNetworkResponse.asDomain(): List<RandomImageModel> {
    return randomImages.map { it.asDomain() }
}