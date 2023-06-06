package com.salvador.artapp.data.repository_impls.paged

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.salvador.artapp.domain.domain_models.exhibit.new_exhibit.ExhibitModel
import com.salvador.artapp.domain.use_cases.GetExhibitsUseCase

class ExhibitionSource(
    private val getExhibitsUseCase: GetExhibitsUseCase
): PagingSource<Int, ExhibitModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ExhibitModel> {
        return try {
            val nextPage = params.key ?: 1
            val exhibits = getExhibitsUseCase(nextPage)
            LoadResult.Page(
                data = exhibits.exhibits,
                prevKey = if(nextPage == 1) null else nextPage -1,
                nextKey = exhibits.pagination.currentPage.plus(1)
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ExhibitModel>): Int? {
        TODO("Not yet implemented")
    }
}