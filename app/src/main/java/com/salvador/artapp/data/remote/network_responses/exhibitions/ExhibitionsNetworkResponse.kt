package com.salvador.artapp.data.remote.network_responses.exhibitions


import com.google.gson.annotations.SerializedName
import com.salvador.artapp.data.remote.network_responses.list.Config
import com.salvador.artapp.data.remote.network_responses.list.Info
import com.salvador.artapp.data.remote.network_responses.list.Pagination

data class ExhibitionsNetworkResponse(

    @SerializedName("data")
    val exhibits: List<Exhibit> = listOf(),

)