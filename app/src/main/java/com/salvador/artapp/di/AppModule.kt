package com.salvador.artapp.di

import android.content.Context
import androidx.room.Room
import com.salvador.artapp.data.db.ArtworkDao
import com.salvador.artapp.data.db.ArtworksDatabase
import com.salvador.artapp.data.remote.api.ArtApi
import com.salvador.artapp.data.repository_impls.ArtworkRepositoryImpl
import com.salvador.artapp.data.repository_impls.ExhibitRepositoryImpl
import com.salvador.artapp.domain.repositories.ArtworkRepository
import com.salvador.artapp.domain.repositories.ExhibitRepository
import com.salvador.artapp.domain.use_cases.*
import com.salvador.artapp.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideArtworksListUseCase(
        artworkRepository: ArtworkRepository
    ): GetArtworksUseCase = GetArtworksUseCase(artworkRepository)

    @Provides
    fun provideSearchArtUseCase(
        artworkRepository: ArtworkRepository
    ): SearchArtUseCase = SearchArtUseCase(artworkRepository)

    @Provides
    fun provideArtDetailUseCase(
        artworkRepository: ArtworkRepository
    ): GetArtDetailUseCase = GetArtDetailUseCase(artworkRepository)

    @Provides
    fun provideExhibitionsUseCase(
        exhibitRepository: ExhibitRepository,
    ): GetExhibitsUseCase = GetExhibitsUseCase(exhibitRepository)

    @Provides
    fun provideRandomArtUseCase(
        artworkRepository: ArtworkRepository
    ): GetRandomArtUseCase = GetRandomArtUseCase(artworkRepository)

    @Provides
    fun provideArtworkDao(artworksDatabase: ArtworksDatabase): ArtworkDao = artworksDatabase.getArtworkDao()

    @Provides
    @Singleton
    fun provideArtworksDatabase(@ApplicationContext context: Context): ArtworksDatabase =
        Room.databaseBuilder(
            context,
            ArtworksDatabase::class.java,
            "artworks_db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideArtworkRepository(
        artApi: ArtApi,
        artworksDatabase: ArtworksDatabase,
    ): ArtworkRepository = ArtworkRepositoryImpl(artApi, artworksDatabase)

    @Provides
    fun provideExhibitRepository(
        artApi: ArtApi,
        artworksDatabase: ArtworksDatabase,
    ) : ExhibitRepository = ExhibitRepositoryImpl(artApi, artworksDatabase)

    @Singleton
    @Provides
    fun provideOkhttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideArtApi(okHttpClient: OkHttpClient): ArtApi =
        Retrofit.Builder().baseUrl(BASE_URL)
            .client(okHttpClient).addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ArtApi::class.java)


}