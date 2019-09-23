package com.danieloliveira138.cinesky.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danieloliveira138.cinesky.utils.TABLE_MOVIES

@Entity(tableName = TABLE_MOVIES)
data class MovieEntity(
    @PrimaryKey var id: String,

    var title: String?,

    var backdrops_url: String?,

    var cover_url: String?,

    var overview: String?,

    var release_year: String?,

    var duration: String? = ""
)