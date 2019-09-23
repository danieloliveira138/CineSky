package com.danieloliveira138.cinesky.models

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import com.danieloliveira138.cinesky.data.MovieEntity
import com.danieloliveira138.cinesky.repository.Repository

class MovieModel {

    val repository = Repository()

    fun fetch(id: String): LiveData<MovieEntity> {
        repository.getMovieFromAPI(id)
        return repository.getMovieFromDB(id)
    }

    fun onError() = repository.movieError

    fun favorite(id: String) = repository.favoriteMovie(id)

    fun isFavorite(id: String) = repository.getFavorite(id)

    fun unFavorite(id: String) = repository.unFavoriteMovie(id)

}