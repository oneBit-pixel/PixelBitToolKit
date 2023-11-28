package com.example.lib_keyboard.typface

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.TypefaceSpan


class CustomTypefaceSpan(private val newTypeface: Typeface, family: String="") : TypefaceSpan(family) {

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
    }

    override fun updateMeasureState(paint: TextPaint) {
        applyCustomTypeFace(paint, newTypeface)
    }

    private fun applyCustomTypeFace(paint: TextPaint, tf: Typeface) {
        paint.typeface = tf

    }

}