package com.danieloliveira138.cinesky.ui.main_screen

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.danieloliveira138.cinesky.R
import com.danieloliveira138.cinesky.data.Movie
import com.danieloliveira138.cinesky.utils.ImageURLHelper
import kotlinx.android.synthetic.main.movie_holder.view.*

class MovieHolder(private val view: View): RecyclerView.ViewHolder(view) {

    fun bind(movie: Movie, clickListener: (Movie) -> Unit) {

        view.movieTitle.text = "${movie.title}"

        var options = RequestOptions()
        options = options.transform(CenterCrop(), RoundedCorners(8))

        Glide
            .with(view.context)
            .load(ImageURLHelper().movieThumb(movie.cover_url))
            .placeholder(R.drawable.placeholder)
            .apply(options)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view.movieThumb)

        view.setOnClickListener { clickListener(movie) }

    }

}