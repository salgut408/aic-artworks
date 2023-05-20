package com.salvador.artapp.data

interface Paginator<Key, ArtworkModel> {
    suspend fun loadMoreArtworks()
    fun reset()
}