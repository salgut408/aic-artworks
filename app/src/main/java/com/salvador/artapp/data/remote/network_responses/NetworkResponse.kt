package com.salvador.artapp.data.remote.network_responses


import com.google.gson.annotations.SerializedName

data class NetworkResponse(
    @SerializedName("config")
    val config: Config? = Config(),
    @SerializedName("data")
    val `data`: List<Data?>? = listOf(),
    @SerializedName("info")
    val info: Info? = Info(),
    @SerializedName("pagination")
    val pagination: Pagination? = Pagination()
)