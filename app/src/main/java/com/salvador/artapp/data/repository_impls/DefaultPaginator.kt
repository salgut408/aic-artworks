package com.salvador.artapp.data.repository_impls

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.salvador.artapp.data.Paginator
import com.salvador.artapp.data.remote.api.ArtApi
import com.salvador.artapp.data.remote.network_responses.asDomain
import com.salvador.artapp.domain.domain_models.ArtworkModel
import com.salvador.artapp.utils.Constants.Companion.FIELD_TERMS

class ArtPagingSource(
   private val artApi: ArtApi,
): PagingSource<Int, ArtworkModel>(){
    override fun getRefreshKey(state: PagingState<Int, ArtworkModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArtworkModel> {
        return try {
            val page = params.key ?: 1
            val response = artApi.getAllArt(fieldTerms = FIELD_TERMS, pageNumber = page )
            LoadResult.Page(
                data = response.body()?.artwork?.map { it.asDomain() }!!,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.body()?.artwork?.isEmpty() == true) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}