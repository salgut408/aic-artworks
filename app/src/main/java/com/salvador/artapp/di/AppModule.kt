package com.salvador.artapp.di

import android.content.Context
import androidx.room.Room
import com.salvador.artapp.data.db.ArtworkDao
import com.salvador.artapp.data.db.ArtworksDatabase
import com.salvador.artapp.data.remote.api.ArtApi
import com.salvador.artapp.data.repository_impls.ArtworkRepositoryImpl
import com.salvador.artapp.domain.repositories.ArtworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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

}