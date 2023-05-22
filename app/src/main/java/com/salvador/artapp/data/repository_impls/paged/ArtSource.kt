package com.salvador.artapp.data.repository_impls.paged

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.salvador.artapp.domain.domain_models.ArtworkModel
import com.salvador.artapp.domain.repositories.ArtworkRepository
import com.salvador.artapp.utils.Constants.Companion.FIELD_TERMS

class ArtSource(
    private val artworkRepository: ArtworkRepository
): PagingSource<Int, ArtworkModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArtworkModel> {
        return try {
            val nextPage = params.key ?: 1
            val artListResponse = artworkRepository.getFullResponse(FIELD_TERMS, nextPage)
            artworkRepository.saveAllArt(artListResponse.artWorks)
            LoadResult.Page(
                data = artListResponse.artWorks,
                prevKey = if(nextPage == 1) null else nextPage -1,
                nextKey = artListResponse.pagination.currentPage.plus(1)
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArtworkModel>): Int? {
        TODO("Not yet implemented")
    }
}