package com.onBit.lib_base.base.utils

import android.graphics.Bitmap
import android.graphics.Color
import androidx.palette.graphics.Palette

object ColorUtilsEx {

    fun interface ColorCallback {
        fun callback(color: Int)
    }

    fun getColorFromBitmap(bitmap: Bitmap,callback: ColorCallback) {
        Palette.from(bitmap)
            .setRegion(0, 0, bitmap.width, 100)
            .generate { palette ->
                palette?.dominantSwatch?.rgb?.also { color ->
                    callback.callback(color)
                }
            }
    }
}