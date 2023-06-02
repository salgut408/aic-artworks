package com.salvador.artapp.data.repository_impls.paged

import androidx.paging.PagingSource
import com.salvador.artapp.domain.domain_models.exhibit.ExhibitModel
import com.salvador.artapp.domain.use_cases.GetExhibitsUseCase

//class ExhibitsSource(
//    private val getExhibitsUseCase: GetExhibitsUseCase
//): PagingSource<Int, ExhibitModel>() {
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ExhibitModel> {
//        return try {
//            val nextPage = params.key ?: 1
//            val exhibitsResponse = getExhibitsUseCase(nextPage)
//            LoadResult.Page(
//                data = exhibitsResponse
//            )
//        }
//    }
//}