package com.danieloliveira138.cinesky.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danieloliveira138.cinesky.utils.TABLE_FAVORITES

@Entity(tableName = TABLE_FAVORITES)
data class FavoriteEntity(var movieId: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}