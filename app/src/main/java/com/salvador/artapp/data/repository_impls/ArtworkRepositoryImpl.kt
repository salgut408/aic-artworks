package com.salvador.artapp.data.repository_impls

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.salvador.artapp.data.db.ArtworksDatabase
import com.salvador.artapp.data.remote.api.ArtApi
import com.salvador.artapp.data.remote.network_responses.detail.NetworkDetail
import com.salvador.artapp.data.remote.network_responses.detail.asDomain
import com.salvador.artapp.data.remote.network_responses.list.asDomain
import com.salvador.artapp.data.remote.network_responses.random_images.RandomImage
import com.salvador.artapp.data.remote.network_responses.random_images.asDomain
import com.salvador.artapp.data.repository_impls.paged.ArtworksRemoteMediator
import com.salvador.artapp.domain.domain_models.detail.ArtDetail
import com.salvador.artapp.domain.domain_models.list.ArtResponseModel
import com.salvador.artapp.domain.domain_models.list.ArtworkModel
import com.salvador.artapp.domain.domain_models.list.asArtworkDbEntity
import com.salvador.artapp.domain.domain_models.random_image.RandomImageModel
import com.salvador.artapp.domain.repositories.ArtworkRepository
import com.salvador.artapp.utils.Constants.Companion.FIELD_TERMS
import kotlinx.coroutines.flow.Flow

class ArtworkRepositoryImpl(
    val artApi: ArtApi,
    val artworksDatabase: ArtworksDatabase
): ArtworkRepository {

    @OptIn(ExperimentalPagingApi::class)
     override  fun getAllImagesModels(): Flow<PagingData<ArtworkModel>>{
        val pagingSourceFactory = {artworksDatabase.getArtworkDao().pagingSourceGetAllArtWorkModels()}
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = ArtworksRemoteMediator(
                artApi = artApi,
                artworksDatabase = artworksDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override suspend fun searchForArtworks(
        fieldTerms: String,
        searchQuery: String,
        pageNumber: Int,
    ): ArtResponseModel =
         artApi.searchForArt(fieldTerms, searchQuery, pageNumber).body()?.asDomain()!!

    override suspend fun saveAllArt(art: List<ArtworkModel>) {
        artworksDatabase.getArtworkDao().insertArtworksList(art.map { it.asArtworkDbEntity() })
    }



    override suspend fun getArtDetail(
        id: String,
    ): ArtDetail {
       val response = artApi.getArtDetails(id)
        if (response.isSuccessful){
            return response.body()?.asDomain()!!
        }
        return response.body()?.asDomain()!!
    }

    override suspend fun getFullResponse(fieldTerms: String, pageNumber: Int) =
        artApi.getAllArt(FIELD_TERMS, pageNumber).body()?.asDomain()!!

    override suspend fun getRandomImages(pageNumber: Int): List<RandomImageModel> {
        val response = artApi.getRandomImages(pageNumber)
        if (response.isSuccessful){
            return response.body()?.randomImages!!.map { it?.asDomain()!! }
        }
        return response.body()?.randomImages?.map { it?.asDomain()!! } !!
    }

    override suspend fun getAllArtFromDb(): List<ArtworkModel> {
        TODO("Not yet implemented")
    }
}