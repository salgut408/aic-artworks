package com.salvador.artapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.salvador.artapp.data.db.artwork_db.ArtworkDbEntity
import com.salvador.artapp.data.db.artwork_db.RemoteKeysDao
import com.salvador.artapp.domain.domain_models.list.ArtworkModel


@Database(
    entities = [ArtworkDbEntity:: class,
        ArtworkModel:: class,
        ArtRemoteKey :: class],
    version = 4,
    exportSchema = false
)

abstract class ArtworksDatabase: RoomDatabase()  {
    abstract fun getArtworkDao(): ArtworkDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao

}