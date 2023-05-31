package com.salvador.artapp.domain.repositories

import com.salvador.artapp.data.remote.network_responses.detail.NetworkDetail
import com.salvador.artapp.data.remote.network_responses.random_images.RandomImage
import com.salvador.artapp.domain.domain_models.detail.ArtDetail
import com.salvador.artapp.domain.domain_models.list.ArtworkModel
import com.salvador.artapp.domain.domain_models.list.ArtResponseModel
import com.salvador.artapp.domain.domain_models.random_image.RandomImageModel

interface ArtworkRepository {
    suspend fun searchForArtworks(fieldTerms: String, searchQuery: String, pageNumber: Int): ArtResponseModel
    suspend fun saveAllArt(art: List<ArtworkModel>)
    suspend fun getArtDetail( id:String): ArtDetail
    suspend fun getFullResponse(fieldTerms: String, pageNumber: Int): ArtResponseModel
    suspend fun getRandomImages(pageNumber: Int): List<RandomImageModel>


}