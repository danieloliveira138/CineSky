package com.danieloliveira138.cinesky.viewmodels

import android.text.SpannableString
import androidx.lifecycle.ViewModel
import com.danieloliveira138.cinesky.models.MovieModel
import com.danieloliveira138.cinesky.utils.TextUtils

class DetailViewModel: ViewModel() {

    val model = MovieModel()

    var favoriteFlag = false

    var movieId: String? = null

    fun fetch(id: String) = model.fetch(id)

    fun onError() = model.onError()

    fun isFavorite(id: String) = model.isFavorite(id)

    fun favorite() = movieId?.let {
        model.favorite(it)
    }

    fun unFavorite() = movieId?.let {
        model.unFavorite(it)
    }

    fun getDuration(duration: String?): SpannableString =
        if (duration == null) SpannableString("")
        else TextUtils.setItalicText("Duração: ", duration)


    fun getRelease(release: String?): SpannableString =
        if (release == null) SpannableString("")
        else TextUtils.setItalicText("Lançamento: ", release)


    fun getOverview(overview: String?): SpannableString =
        if (overview == null) SpannableString("")
        else TextUtils.setBoldText("Sinopse: ", overview)

}