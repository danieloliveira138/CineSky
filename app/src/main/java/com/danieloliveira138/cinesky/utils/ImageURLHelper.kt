package com.danieloliveira138.cinesky.utils

class ImageURLHelper {

    companion object {
        const val thumbMovieRightSize = "w1280"
        const val backdropRightSize = "w1066_and_h600_bestv2"
    }

    fun movieThumb(url: String?): String =

        when{

            url == null -> ""

            url.contains("w640") ->
                url.replace("w640", thumbMovieRightSize)

            url.contains("w200_and_h300_bestv2") ->
                url.replace("w200_and_h300_bestv2", thumbMovieRightSize)

            url.contains("w1280") ->
                url.replace("w1280", thumbMovieRightSize)

            else -> url

        }

    fun movieBackdrops(url: String?): String =

            when{

                url == null -> ""

                url.contains("w1300_and_h730_bestv2") ->
                    url.replace("w1300_and_h730_bestv2", backdropRightSize)

                url.contains("w533_and_h300_bestv2") ->
                    url.replace("w533_and_h300_bestv2", backdropRightSize)

                url.contains("original") ->
                    url.replace("original", backdropRightSize)

                else -> url
            }

}