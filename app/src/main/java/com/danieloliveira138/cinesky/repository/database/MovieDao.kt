package com.danieloliveira138.cinesky.repository.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.danieloliveira138.cinesky.data.MovieEntity
import com.danieloliveira138.cinesky.utils.TABLE_MOVIES

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: List<MovieEntity>)

    @Query("SELECT * FROM $TABLE_MOVIES")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM $TABLE_MOVIES WHERE id = :id")
    fun getMovie(id: String): LiveData<MovieEntity>
}