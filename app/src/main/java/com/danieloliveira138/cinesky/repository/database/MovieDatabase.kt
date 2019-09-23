package com.danieloliveira138.cinesky.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.danieloliveira138.cinesky.data.FavoriteEntity
import com.danieloliveira138.cinesky.data.MovieEntity

@Database(version = 1, entities = [MovieEntity::class, FavoriteEntity::class])
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun favorites(): FavoriteDao
}