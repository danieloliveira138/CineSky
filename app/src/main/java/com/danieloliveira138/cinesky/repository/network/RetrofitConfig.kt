package com.danieloliveira138.cinesky.repository.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class RetrofitConfig {

    var okHttpClient = OkHttpClient.Builder()
                    .addNetworkInterceptor(StethoInterceptor())
                    .build()

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://sky-exercise.herokuapp.com/api/")
        .client(okHttpClient)
        .addConverterFactory(JacksonConverterFactory.create())
        .build()

    fun moviesService(): MoviesService = retrofit.create(MoviesService::class.java)

}