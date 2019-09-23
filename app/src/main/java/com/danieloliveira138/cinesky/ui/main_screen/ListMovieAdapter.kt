package com.danieloliveira138.cinesky.ui.main_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danieloliveira138.cinesky.R
import com.danieloliveira138.cinesky.data.Movie

class ListMovieAdapter(private val clickListener: (Movie) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listMovies = mutableListOf<Movie>()

    fun setMovieList(list: List<Movie>) {
        listMovies.clear()
        listMovies.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_holder, parent, false)
        return MovieHolder(view)
    }

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder as MovieHolder
        holder.bind(listMovies[position], clickListener)

    }

}