package com.danieloliveira138.cinesky.models

import androidx.lifecycle.LiveData
import com.danieloliveira138.cinesky.data.MovieEntity
import com.danieloliveira138.cinesky.repository.Repository

class MoviesModel {

    private val repository = Repository()

    fun fetch(): LiveData<List<MovieEntity>> {
        repository.getMoviesFromAPI()
        return repository.getMoviesFromDB()
    }

    fun onError() = repository.moviesError

}