package com.example.lib_view.edgeView

import android.content.Context
import android.util.AttributeSet
import android.view.View

class EdgeBorderLightView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet,
    defStyle: Int
) : View(context, attr, defStyle) {

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

    }
}