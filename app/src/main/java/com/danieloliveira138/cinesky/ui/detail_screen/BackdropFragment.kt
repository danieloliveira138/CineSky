package com.danieloliveira138.cinesky.ui.detail_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.danieloliveira138.cinesky.R
import com.danieloliveira138.cinesky.utils.ImageURLHelper
import kotlinx.android.synthetic.main.pager_image_view.view.*

class BackdropFragment(private val image: String): Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.pager_image_view, container, false)

        Glide.with(view.context)
            .load(ImageURLHelper().movieBackdrops(image))
            .fitCenter()
            .into(view.imageView)

        return view

    }

}