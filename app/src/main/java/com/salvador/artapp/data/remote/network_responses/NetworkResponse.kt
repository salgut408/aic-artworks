package com.salvador.artapp.data.remote.network_responses


import com.google.gson.annotations.SerializedName
import com.salvador.artapp.domain.domain_models.ArtResponseModel

data class NetworkResponse(
    @SerializedName("config")
    val config: Config = Config(),
    @SerializedName("data")
    val artwork: List<ArtDataNetwork> = listOf(),
    @SerializedName("info")
    val info: Info = Info(),
    @SerializedName("pagination")
    val pagination: Pagination = Pagination()
)

fun NetworkResponse.asDomain(): ArtResponseModel {
    return ArtResponseModel(
        config = config.asDomain(),
        artWorks = artwork.map { it.asDomain() },
        info = info.asDomain(),
        pagination = pagination.asDomain()
    )
}