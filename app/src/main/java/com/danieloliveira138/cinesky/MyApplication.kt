package com.danieloliveira138.cinesky

import android.app.Application
import androidx.room.Room
import com.danieloliveira138.cinesky.repository.database.MovieDatabase
import com.danieloliveira138.cinesky.utils.MOVIE_DATABASE
import com.facebook.stetho.Stetho
import timber.log.Timber

@Suppress("unused")
class MyApplication: Application() {

    companion object {
        var database: MovieDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this, MovieDatabase::class.java, MOVIE_DATABASE).build()

        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

        Stetho.initializeWithDefaults(this)

    }

    override fun onTerminate() {
        super.onTerminate()
        database?.close()
    }
}