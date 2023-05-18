package com.salvador.artapp.data.remote.network_responses


import com.google.gson.annotations.SerializedName
import com.salvador.artapp.domain.domain_models.ResponseModel

data class NetworkResponse(
    @SerializedName("config")
    val config: Config = Config(),
    @SerializedName("data")
    val artwork: List<Data> = listOf(),
    @SerializedName("info")
    val info: Info = Info(),
    @SerializedName("pagination")
    val pagination: Pagination = Pagination()
)

fun NetworkResponse.asDomain(): ResponseModel {
    return ResponseModel(
        config = config.asDomain(),
        artWork = artwork.map { it.asDomain() },
        info = info.asDomain(),
        pagination = pagination.asDomain()
    )
}