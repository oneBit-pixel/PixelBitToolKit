package com.onBit.lib_base.base.extension

import android.content.Context
import android.util.TypedValue

fun Float.dp2px(context: Context) : Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,this, context.resources.displayMetrics)
}

fun Float.sp2px(context: Context) : Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,this, context.resources.displayMetrics)
}