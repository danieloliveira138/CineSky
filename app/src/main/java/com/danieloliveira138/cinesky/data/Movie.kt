package com.danieloliveira138.cinesky.data

import java.io.Serializable

data class Movie(
    var backdrops_url: List<String>? = emptyList(),
    var cover_url: String? = "",
    var duration: String? = "",
    var id: String? = "",
    var overview: String? = "",
    var release_year: String? = "",
    var title: String? = ""
): Serializable