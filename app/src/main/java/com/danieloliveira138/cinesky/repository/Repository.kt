package com.danieloliveira138.cinesky.repository

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.danieloliveira138.cinesky.MyApplication
import com.danieloliveira138.cinesky.data.FavoriteEntity
import com.danieloliveira138.cinesky.data.Movie
import com.danieloliveira138.cinesky.data.MovieEntity
import com.danieloliveira138.cinesky.repository.network.RetrofitConfig
import com.danieloliveira138.cinesky.utils.toMovieEntityList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class Repository {
    val moviesError = MutableLiveData<Throwable>()
    val movieError = MutableLiveData<Throwable>()
    val service = RetrofitConfig().moviesService()
    val database = MyApplication.database

    fun getMoviesFromAPI() =
        service.getMovies().enqueue(
            object : Callback<List<Movie>> {
                override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                    Timber.tag("MOVIE").d("failure + ${t.message}")
                    moviesError.postValue(t)
                }

                override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                    response.body()?.let {
                        it.map { Timber.tag("MOVIES").d("${it.title}") }
                        insertMovieToDB(it)
                    }
                }

            }
        )

    fun getMovieFromAPI(id: String) =
        service.getMovie(id).enqueue(
            object : Callback<Movie> {
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Timber.tag("MOVIE").d("failure + ${t.message}")
                    movieError.postValue(t)
                }

                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    response.body()?.let {
                        Timber.tag("MOVIES").d("${it.title}")
                    }
                }

            }
        )

    fun getMoviesFromDB(): LiveData<List<MovieEntity>> = database?.movieDao()!!.getMovies()

    fun getMovieFromDB(id: String): LiveData<MovieEntity> = database?.movieDao()!!.getMovie(id)

    fun getFavorite(id: String): LiveData<FavoriteEntity> = database?.favorites()!!.isFavorite(id)

    fun insertMovieToDB(movies: List<Movie>) {
        val listEntity = movies.toMovieEntityList()
        AsyncTask.execute{
            database?.movieDao()?.insert(listEntity)
        }
    }

    fun favoriteMovie(id: String) {
        val favorite = FavoriteEntity(movieId = id)
        AsyncTask.execute{
            database?.favorites()?.favorite(favorite)
        }
    }

    fun unFavoriteMovie(id: String) {
        AsyncTask.execute{
            database?.favorites()?.unFavorite(movieId = id)
        }
    }

}