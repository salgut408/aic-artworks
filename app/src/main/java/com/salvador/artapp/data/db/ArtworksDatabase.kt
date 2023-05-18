package com.salvador.artapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.salvador.artapp.data.db.artwork_db.ArtworkDbEntity


@Database(
    entities = [ArtworkDbEntity:: class, ],
    version = 1,
    exportSchema = false
)

abstract class ArtworksDatabase: RoomDatabase()  {
    abstract fun getArtworkDao(): ArtworkDao
}