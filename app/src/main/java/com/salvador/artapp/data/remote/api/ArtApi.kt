package com.salvador.artapp.data.remote.api

import com.salvador.artapp.data.remote.network_responses.NetworkResponse
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
        @Query("fields")
        fieldTerms: String,
        @Query("page")
        pageNumber: Int = 1,
        @Path("id")
        id: String? = null

    ): Response<NetworkResponse>

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



//    /artworks/{id}
}