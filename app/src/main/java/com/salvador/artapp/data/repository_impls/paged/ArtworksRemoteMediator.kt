package com.salvador.artapp.data.repository_impls.paged

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.salvador.artapp.data.db.ArtworksDatabase
import com.salvador.artapp.data.db.artwork_db.ArtworkDbEntity
import com.salvador.artapp.data.remote.api.ArtApi
import com.salvador.artapp.data.remote.network_responses.list.asDomain
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
    artworkRepository: ArtworkRepository
): RemoteMediator<Int, ArtworkDbEntity>() {

    private val artworksDao = artworksDatabase.getArtworkDao()


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArtworkDbEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()

                    if (lastItem == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }

                    lastItem.id
                }
            }

            val response = artApi.getAllArt(FIELD_TERMS, 1,)

            artworksDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    artworksDao.clearAll()
                }
                artworksDao.insertArtworksList(response.body()?.artwork?.map {
                    it.asDomain().asArtworkDbEntity()
                }!!)
            }


            MediatorResult.Success(
                endOfPaginationReached = response.body()?.pagination?.currentPage == null
            )
        }
        catch (e: IOException) {
            MediatorResult.Error(e)
        }
        catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}