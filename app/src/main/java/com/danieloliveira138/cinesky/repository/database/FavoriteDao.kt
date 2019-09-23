package com.danieloliveira138.cinesky.repository.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.danieloliveira138.cinesky.data.FavoriteEntity
import com.danieloliveira138.cinesky.utils.TABLE_FAVORITES

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun favorite(favorite: FavoriteEntity)

    @Query("DELETE FROM $TABLE_FAVORITES WHERE movieId = :movieId")
    fun unFavorite(movieId: String)

    @Query("SELECT * FROM $TABLE_FAVORITES WHERE movieId = :movieId")
    fun isFavorite(movieId: String): LiveData<FavoriteEntity>

}