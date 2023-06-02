package com.salvador.artapp.data.repository_impls.paged

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.salvador.artapp.data.db.ArtRemoteKey
import com.salvador.artapp.data.db.ArtworksDatabase
import com.salvador.artapp.data.db.artwork_db.ArtworkDbEntity
import com.salvador.artapp.data.remote.api.ArtApi
import com.salvador.artapp.data.remote.network_responses.list.asDomain
import com.salvador.artapp.domain.domain_models.list.ArtworkModel
import com.salvador.artapp.domain.domain_models.list.asArtworkDbEntity
import com.salvador.artapp.domain.repositories.ArtworkRepository
import com.salvador.artapp.utils.Constants.Companion.FIELD_TERMS
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ArtworksRemoteMediator @Inject constructor(
    private val artApi: ArtApi,
    private val artworksDatabase: ArtworksDatabase,
): RemoteMediator<Int, ArtworkModel>() {

    private val artworksDao = artworksDatabase.getArtworkDao()
    private val remoteKeysDao = artworksDatabase.getRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArtworkModel>
    ): MediatorResult {
        return try {
            val currentPage = when(loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeysForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }
            val response = artApi.getAllArt(FIELD_TERMS, currentPage).body()
//                artworkRepository.getFullResponse(FIELD_TERMS, currentPage)
            val endOfPaginationReached = response?.pagination?.nextUrl?.isEmpty()

            val prevPage = if(currentPage == 1) null else currentPage - 1
            val nextPage = if(endOfPaginationReached == true) null else currentPage + 1

            artworksDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    artworksDao.clearAllArtworkModels()
                    remoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = response?.artwork?.map { artWork ->
                    ArtRemoteKey(
                        id = artWork.id.toString(),
                        prevPage = prevPage,
                        nextPage = nextPage!!
                    )
                }
                remoteKeysDao.addAllRemoteKeys(remoteKeys = keys ?: listOf())
                artworksDao.insertArtworksModelList(response?.artwork?.map { it.asDomain() }!!)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached == true)
        }
        catch (e: IOException) {
            MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeysForLastItem(
        state: PagingState<Int, ArtworkModel>
    ): ArtRemoteKey? {
        return state.pages.lastOrNull {it.data.isNotEmpty()}?.data?.lastOrNull()
            ?.let { art ->
                remoteKeysDao.getRemoteKeys(id = art.id.toString())
            }
    }

    private suspend fun getRemoteKeysForFirstItem(
        state: PagingState<Int, ArtworkModel>
    ): ArtRemoteKey? {
        return state.pages.firstOrNull {it.data.isNotEmpty()}?.data?.firstOrNull()
            ?.let { artwork ->
                remoteKeysDao.getRemoteKeys(artwork.id.toString())
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, ArtworkModel>
    ): ArtRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeysDao.getRemoteKeys(id = id.toString())
            }
        }
    }


}