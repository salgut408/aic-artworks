package com.salvador.artapp.data.remote.network_responses.detail


import com.google.gson.annotations.SerializedName
import com.salvador.artapp.domain.domain_models.detail.ArtDetail

data class NetworkDetail(

    @SerializedName("data")
    val networkArtData: DataDetailNetwork? = DataDetailNetwork(),

)

fun NetworkDetail.asDomain(): ArtDetail {
    return ArtDetail(
        artData = networkArtData?.asDomain()
    )
}
