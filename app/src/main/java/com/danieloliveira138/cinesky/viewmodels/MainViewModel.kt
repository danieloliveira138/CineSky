package com.danieloliveira138.cinesky.viewmodels

import androidx.lifecycle.ViewModel
import com.danieloliveira138.cinesky.models.MoviesModel

class MainViewModel: ViewModel() {

    val model = MoviesModel()

    fun fetch() = model.fetch()

    fun error() = model.onError()
}