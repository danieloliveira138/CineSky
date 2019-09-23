package com.danieloliveira138.cinesky.repository.network

import com.danieloliveira138.cinesky.data.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesService {

    @GET("Movies")
    fun getMovies(): Call<List<Movie>>

    @GET("Movies/{id}")
    fun getMovie(@Path("id") id: String): Call<Movie>

}