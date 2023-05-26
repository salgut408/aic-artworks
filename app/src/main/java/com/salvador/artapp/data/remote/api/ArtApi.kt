package com.salvador.artapp.data.remote.api

import com.salvador.artapp.data.remote.network_responses.detail.NetworkDetail
import com.salvador.artapp.data.remote.network_responses.exhibitions.ExhibitionsNetworkResponse
import com.salvador.artapp.data.remote.network_responses.list.NetworkResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtApi {

    @GET("artworks/search")
    suspend fun getAllArt(
        @Query("fields")
        fieldTerms: String,
        @Query("page")
        pageNumber: Int
    ): Response<NetworkResponse>

    @GET("artworks/search")
    suspend fun getAllArtPaged(
        @Query("fields")
        fieldTerms: String,
        @Query("page")
        pageNumber: Int
    ): Result<NetworkResponse>

    @GET("artworks/{id}")
    suspend fun getArtDetails(
        @Path("id")
        id: String? = null
    ): Response<NetworkDetail>

    @GET("artworks/search")
    suspend fun searchForArt(
        @Query("fields")
        fieldTerms: String,
        @Query("q")
        searchTerm: String,
        @Query("page")
        pageNumber: Int = 1
    ): Response<NetworkResponse>

    @GET("artworks/search")
    suspend fun searchForArtStyleType(
        @Query("fields")
        fieldTerms: String,
        @Query("q")
        searchTerm: String,
        @Query("page")
        pageNumber: Int = 1
    ): Response<NetworkResponse>


    @GET("exhibitions")
    suspend fun getExhibitions(
        @Query("page")
        pageNumber: Int
    ): Response<ExhibitionsNetworkResponse>

//    https://api.artic.edu/api/v1/exhibitions
//    ExhibitionsNetworkResponse
// //    /artworks/{id}
}