package com.danieloliveira138.cinesky

import com.danieloliveira138.cinesky.utils.ImageURLHelper
import org.junit.Assert
import org.junit.Test

class CoverUrlFixUnitTest {

    @Test
    fun assert_url_w640(){
        val url = "https://image.tmdb.org/t/p/w640/jAXZMCG9rEXHUvfxTwEYEmO1V4p.jpg"
        val fixUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/jAXZMCG9rEXHUvfxTwEYEmO1V4p.jpg"
        val baseUrl = url.replace("w640", "w300_and_h450_bestv2")
        print(baseUrl)
        Assert.assertEquals(fixUrl, baseUrl)
    }

    @Test
    fun assert_url_method(){
        val url = "https://image.tmdb.org/t/p/w640/jAXZMCG9rEXHUvfxTwEYEmO1V4p.jpg"
        val fixUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/jAXZMCG9rEXHUvfxTwEYEmO1V4p.jpg"
        print(ImageURLHelper().movieThumb(url))
        Assert.assertEquals(fixUrl, ImageURLHelper().movieThumb(url))
    }

    @Test
    fun assert_url_w200_h300(){
        val url = "https://image.tmdb.org/t/p/w200_and_h300_bestv2/ujQthWB6c0ojlARk28NSTmqidbF.jpg"
        val fixUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/ujQthWB6c0ojlARk28NSTmqidbF.jpg"
        val fixedURl = url.replace("w200_and_h300_bestv2", "w300_and_h450_bestv2")
        print(fixedURl)
        Assert.assertEquals(fixUrl, fixedURl)
    }

    @Test
    fun assert_url_method_2(){
        val url = "https://image.tmdb.org/t/p/w200_and_h300_bestv2/ujQthWB6c0ojlARk28NSTmqidbF.jpg"
        val fixUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/ujQthWB6c0ojlARk28NSTmqidbF.jpg"
        print(ImageURLHelper().movieThumb(url))
        Assert.assertEquals(fixUrl, ImageURLHelper().movieThumb(url))
    }

    @Test
    fun assert_matches_query(){
        val url = "https://image.tmdb.org/t/p/w200_and_h300_bestv2/ujQthWB6c0ojlARk28NSTmqidbF.jpg"
        val fixUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/ujQthWB6c0ojlARk28NSTmqidbF.jpg"
        var fixedURL = ""
        if (url.contains("w200_and_h300_bestv2".toRegex())){
            fixedURL = url.replace("w200_and_h300_bestv2", "w300_and_h450_bestv2")
        }
        print(fixedURL)
        Assert.assertEquals(fixUrl, fixedURL)
    }

    @Test
    fun assert_url_w1280(){
        val url = "https://image.tmdb.org/t/p/w1280/dsAQmTOCyMDgmiPp9J4aZTvvOJP.jpg"
        val fixUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/dsAQmTOCyMDgmiPp9J4aZTvvOJP.jpg"
        val fixedURl = url.replace("w1280", "w300_and_h450_bestv2")
        print(fixedURl)
        Assert.assertEquals(fixUrl, fixedURl)
    }

    @Test
    fun assert_url_w1300_and_h730(){
        val url = "https://image.tmdb.org/t/p/w1300_and_h730_bestv2/6RZe2Ykf5cOgLJL8UdMN9fIpIIc.jpg"
        val fixUrl = "https://image.tmdb.org/t/p/w533_and_h300_bestv2/6RZe2Ykf5cOgLJL8UdMN9fIpIIc.jpg"
        val fixedURL = url.replace("w1300_and_h730", "w533_and_h300")
        print(fixedURL)
        Assert.assertEquals(fixUrl, fixedURL)
    }

    @Test
    fun assert_url_w1066_and_h600(){
        val url = "https://image.tmdb.org/t/p/w1066_and_h600_bestv2/tFI8VLMgSTTU38i8TIsklfqS9Nl.jpg"
        val fixUrl = "https://image.tmdb.org/t/p/w533_and_h300_bestv2/tFI8VLMgSTTU38i8TIsklfqS9Nl.jpg"
        val fixedURL = url.replace("w1066_and_h600", "w533_and_h300")
        print(fixedURL)
        Assert.assertEquals(fixUrl, fixedURL)
    }

    @Test
    fun assert_url_w533_and_h300(){
        val url = "https://image.tmdb.org/t/p/w1300_and_h730_bestv2/6RZe2Ykf5cOgLJL8UdMN9fIpIIc.jpg"
        val fixUrl = "https://image.tmdb.org/t/p/w1066_and_h600_bestv2/6RZe2Ykf5cOgLJL8UdMN9fIpIIc.jpg"
        val fixedURL = ImageURLHelper().movieBackdrops(url)
        print(fixedURL)
        Assert.assertEquals(fixUrl, fixedURL)
    }

    @Test
    fun assert_url_original(){
        val url = "https://image.tmdb.org/t/p/original/6RZe2Ykf5cOgLJL8UdMN9fIpIIc.jpg"
        val fixUrl = "https://image.tmdb.org/t/p/w1066_and_h600_bestv2/6RZe2Ykf5cOgLJL8UdMN9fIpIIc.jpg"
        val fixedURL = ImageURLHelper().movieBackdrops(url)
        print(fixedURL)
        Assert.assertEquals(fixUrl, fixedURL)
    }

}