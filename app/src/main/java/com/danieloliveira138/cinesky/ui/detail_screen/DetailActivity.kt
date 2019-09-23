package com.danieloliveira138.cinesky.ui.detail_screen

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.danieloliveira138.cinesky.R
import com.danieloliveira138.cinesky.data.Movie
import com.danieloliveira138.cinesky.utils.ImageURLHelper
import com.danieloliveira138.cinesky.utils.MOVIE_BUNDLE
import com.danieloliveira138.cinesky.utils.toMovie
import com.danieloliveira138.cinesky.viewmodels.DetailViewModel
import kotlinx.android.synthetic.main.detail_activity.*
import timber.log.Timber
import com.danieloliveira138.cinesky.utils.TAG
import jp.wasabeef.glide.transformations.BlurTransformation
import okio.blackholeSink
import java.security.AccessController.getContext

class DetailActivity: AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(DetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right)
        setContentView(R.layout.detail_activity)
        setupToolbar()
        getBundle()
        setupUi()
    }

    private fun setupUi() {
        nestedScroller.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (scrollY > oldScrollY) {
                    floatingLike.hide()
                } else {
                    floatingLike.show()
                }
            }
        )

        floatingLike.setOnClickListener {
            if (!viewModel.favoriteFlag) {
                viewModel.favorite()
            } else {
                viewModel.unFavorite()
            }
        }
    }

    private fun setupToolbar() {
        collapsingToolbar.title = "Cine SKY"
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun insertData(movie: Movie){

        Glide.with(this)
            .asDrawable()
            .load(ImageURLHelper().movieThumb(movie.cover_url))
            .apply(bitmapTransform(BlurTransformation(25,3)))
            .into(object : CustomTarget<Drawable>(){
                override fun onLoadCleared(placeholder: Drawable?) {
                    coordinatorLayout.setBackgroundColor(ContextCompat.getColor(baseContext, R.color.black))
                }

                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    coordinatorLayout.background = resource
                }

            })

        Glide.with(this)
            .asBitmap()
            .load(ImageURLHelper().movieThumb(movie.cover_url))
            .placeholder(R.drawable.placeholder)
            .fitCenter()
            .into(object : CustomTarget<Bitmap>(){
                override fun onLoadCleared(placeholder: Drawable?) {
                    topPanelImage.setImageBitmap(placeholder?.toBitmap())
                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    topPanelImage.setImageBitmap(resource)
                    Timber.tag(TAG).d("Bitmap size: ${resource.byteCount}")
                    createPallete(resource)
                }
            })

        viewPager.adapter = DetailPageAdapter(movie.backdrops_url!!, supportFragmentManager)
        dotsIndicator.setViewPager(viewPager)
        collapsingToolbar.title = movie.title
        overview.text = viewModel.getOverview(movie.overview)
        releaseDate.text = viewModel.getRelease(movie.release_year)
        duration.text = viewModel.getDuration(movie.duration)

    }

    private fun getBundle(){
        if(intent.hasExtra(MOVIE_BUNDLE)) {
            requestData(intent.getStringExtra(MOVIE_BUNDLE))
            viewModel.movieId = intent.getStringExtra(MOVIE_BUNDLE)
            return
        }
        this.finish()
    }

    private fun requestData(movieId: String?) {
        movieId?.let {
            viewModel.fetch(it).observe(this, Observer { movie ->
                movie?.let {
                    Timber.tag(TAG).e("$it")
                    insertData(it.toMovie())
                }
            })

            viewModel.isFavorite(movieId).observe(this, Observer { favorite ->
                viewModel.favoriteFlag = favorite != null
                fabColor()
                Timber.tag(TAG).d("The response is $favorite")
            })
        }
    }

    private fun fabColor() {
        if(viewModel.favoriteFlag) {
            floatingLike.setImageDrawable(ContextCompat.getDrawable(baseContext, R.drawable.ic_favorite))
        } else {
            floatingLike.setImageDrawable(ContextCompat.getDrawable(baseContext, R.drawable.ic_favorite_border))
        }
    }

    private fun createPallete(bitmap: Bitmap) {
        Palette.from(bitmap).generate { palette ->
            palette?.let {
                setupColors(it)
            }
        }
    }

    private fun setupColors(palette: Palette) =
        palette.let {
            viewPager.setBackgroundColor(it.dominantSwatch?.rgb ?: R.color.white)
            releaseDate.setTextColor(it.dominantSwatch?.rgb ?: R.color.black)
            duration.setTextColor(it.dominantSwatch?.rgb ?: R.color.black)
            overview.setTextColor(it.dominantSwatch?.rgb ?: R.color.black)
        }
}