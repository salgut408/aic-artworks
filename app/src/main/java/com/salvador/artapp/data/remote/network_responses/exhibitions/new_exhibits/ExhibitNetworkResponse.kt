package com.salvador.artapp.data.remote.network_responses.exhibitions.new_exhibits


import com.google.gson.annotations.SerializedName
import com.salvador.artapp.domain.domain_models.exhibit.new_exhibit.FullExhibitModel

data class ExhibitNetworkResponse(
    @SerializedName("config")
    val config: Config = Config(),
    @SerializedName("data")
    val exhibits: List<ExhibitNetwork> = listOf(),
    @SerializedName("info")
    val info: Info? = Info(),
    @SerializedName("pagination")
    val pagination: Pagination = Pagination()
)

fun ExhibitNetworkResponse.asDomain(): FullExhibitModel {
    return FullExhibitModel(
        config = config.asDomain(),
        exhibits = exhibits.map { it.asDomain() },
        pagination = pagination.asDomain()
    )
}