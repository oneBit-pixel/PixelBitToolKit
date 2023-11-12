package com.example.lib_view.edgeView

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.example.lib_view.edgeView.animate.EdgeAnimate

class EdgeBorderLightView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet?=null,
    defStyle: Int = 0
) : View(context, attr, defStyle) {

    private val animate: EdgeAnimate by lazy { EdgeAnimate() }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        animate.onLayout(width, height)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        animate.onDraw(canvas)
        postInvalidateDelayed(30)
    }

}