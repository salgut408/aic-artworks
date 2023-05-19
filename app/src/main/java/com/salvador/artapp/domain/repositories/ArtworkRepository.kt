package com.salvador.artapp.domain.repositories

import com.salvador.artapp.domain.domain_models.ArtworkModel

interface ArtworkRepository {
    suspend fun getArtworks(fieldTerms: String, pageNumber: Int): List<ArtworkModel>
    suspend fun searchForArtworks(fieldTerms: String, searchQuery: String, pageNumber: Int): List<ArtworkModel>
    suspend fun saveAllArt(art: List<ArtworkModel>)

}