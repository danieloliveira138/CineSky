package com.danieloliveira138.cinesky.ui.detail_screen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class DetailPageAdapter(
    private val listImages: List<String>,
    private val fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager, listImages.size) {

    override fun getItem(position: Int): Fragment = BackdropFragment(listImages[position])

    override fun getCount(): Int = listImages.size

}