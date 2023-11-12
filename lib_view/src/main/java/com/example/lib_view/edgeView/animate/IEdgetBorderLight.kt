package com.example.lib_view.edgeView.animate

import android.graphics.Bitmap
import android.graphics.Canvas

interface IEdgeBorderLight {
    fun changeColor(iArr: IntArray)

    fun onDraw(canvas: Canvas)

    fun onLayout(width: Int, height: Int)

    fun setBitmap(bitmap: Bitmap)

}