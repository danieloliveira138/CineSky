package com.danieloliveira138.cinesky.utils

import androidx.room.TypeConverter
import com.danieloliveira138.cinesky.data.Movie
import com.danieloliveira138.cinesky.data.MovieEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@TypeConverter
fun List<String>.backDropToString(): String =
    let {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(it, type)
    }

@TypeConverter
fun String.toBackDropList(): List<String> =
        let {
            val gson = Gson()
            val type = object : TypeToken<List<String>>() {}.type
            return gson.fromJson(it, type)
        }

fun List<Movie>.toMovieEntityList(): List<MovieEntity> =
        map {
            MovieEntity(
                id = it.id!!,
                title = it.title,
                backdrops_url = it.backdrops_url?.backDropToString(),
                cover_url = it.cover_url,
                duration = it.duration,
                overview = it.overview,
                release_year = it.release_year
        ) }.toList()

fun List<MovieEntity>.toMovieList(): List<Movie> =
        map {
            Movie(
                id = it.id,
                title = it.title,
                backdrops_url = it.backdrops_url?.toBackDropList(),
                release_year = it.release_year,
                overview = it.overview,
                duration = it.duration,
                cover_url = it.cover_url
            )
        }

fun MovieEntity.toMovie(): Movie =
        let {
            Movie(
                id = it.id,
                cover_url = it.cover_url,
                duration = it.duration,
                overview = it.overview,
                release_year = it.release_year,
                backdrops_url = it.backdrops_url?.toBackDropList(),
                title = it.title
            )
        }