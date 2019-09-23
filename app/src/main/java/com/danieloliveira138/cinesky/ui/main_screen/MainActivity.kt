package com.danieloliveira138.cinesky.ui.main_screen

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.danieloliveira138.cinesky.R
import com.danieloliveira138.cinesky.data.Movie
import com.danieloliveira138.cinesky.data.MovieEntity
import com.danieloliveira138.cinesky.ui.detail_screen.DetailActivity
import com.danieloliveira138.cinesky.ui.dialogs.DialogAlert
import com.danieloliveira138.cinesky.utils.MOVIE_BUNDLE
import com.danieloliveira138.cinesky.utils.toMovieList
import com.danieloliveira138.cinesky.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.main_activity.*
import timber.log.Timber
import com.danieloliveira138.cinesky.utils.TAG

class MainActivity: AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private val adapter = ListMovieAdapter { movie: Movie -> movieItemClicked(movie)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right)
        setContentView(R.layout.main_activity)

        setupAppBar()
        setupListeners()
        setupRecyclerView()
        setupData()
        swipeRefreshLayout.isRefreshing = true

    }

    private fun setupData() {
        viewModel.fetch().observe(this, Observer { listMovies -> setupUi(listMovies) })
        viewModel.error().observe(this, Observer { throwable -> onDataError(throwable) })
    }

    private fun onDataError(throwable: Throwable) {
        Timber.tag(TAG).d("throwble: ${throwable.message}")
        requestProblemAlert()
        swipeRefreshLayout.isRefreshing = false
    }

    private fun setupUi(list: List<MovieEntity>) {
        for(movie in list){
            Timber.tag(TAG).e("movie: ${movie.title}\n")
        }
        adapter.setMovieList(list.toMovieList())
        swipeRefreshLayout.isRefreshing = false
    }

    private fun setupRecyclerView() {

        val orientation = this.resources.configuration.orientation

        val spanCount = if (orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 4

        recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, spanCount)
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupListeners() {

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetch()
        }

    }

    private fun requestList() {
        viewModel.fetch()
        swipeRefreshLayout.isRefreshing = true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun setupAppBar(){
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.title = "Cine SKY"
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    private fun movieItemClicked(movie: Movie){
        Timber.tag(TAG).e(movie.id)
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(MOVIE_BUNDLE, movie.id)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun requestProblemAlert(){
        val requestDialog = DialogAlert(
            {requestList()},
            "Ops...",
            "Algum problema aconteceu e foi impossível acessar os dados da API.\n\nDeseja tentar novamente?")

        requestDialog.show(supportFragmentManager, "dialog_request")
    }

}