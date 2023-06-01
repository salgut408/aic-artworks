//package com.salvador.artapp.data.repository_impls.paged
//
//import androidx.paging.PagingSource
//import com.salvador.artapp.domain.domain_models.list.ArtworkModel
//import com.salvador.artapp.domain.domain_models.random_image.RandomImageModel
//import com.salvador.artapp.domain.use_cases.GetRandomArtUseCase
//
//class RandomArtSource(
//    private val getRandomArtUseCase: GetRandomArtUseCase
//): PagingSource<Int, RandomImageModel>()  {
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RandomImageModel> {
////        return try {
////            val nextPage = params.key ?: 1
////            val artListResponse = getRandomArtUseCase(nextPage)
////            LoadResult.Page(
////                data = artListResponse,
////                prevKey = if(nextPage == 1) null else nextPage - 1,
////                nextKey =
////            )
////        }
//    }
//}