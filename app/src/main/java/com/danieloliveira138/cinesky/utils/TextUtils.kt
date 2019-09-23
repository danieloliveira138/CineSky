package com.danieloliveira138.cinesky.utils

import android.graphics.Typeface
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan

class TextUtils {
    companion object {

        private const val fontSize: Float = 1.2f

        fun setItalicText(text: String, paragraph: String): SpannableString {
            val sentence = text + paragraph
            val italicText = SpannableString(sentence)
            italicText.setSpan(StyleSpan(Typeface.ITALIC), 0, text.length, 0)
            italicText.setSpan(RelativeSizeSpan(fontSize), 0, text.length, 0)
            return italicText
        }

        fun setBoldText(text: String, paragraph: String): SpannableString {
            val sentence = text + paragraph
            val strongText = SpannableString(sentence)
            strongText.setSpan(StyleSpan(Typeface.BOLD), 0, text.length, 0)
            strongText.setSpan(RelativeSizeSpan(fontSize), 0, text.length, 0)
            return strongText
        }
    }
}