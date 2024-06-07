package com.onBit.lib_base.base.extension

import android.content.Context
import android.util.TypedValue
import com.onBit.lib_base.base.init.appContext

fun Float.dp2px(context: Context) : Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,this, context.resources.displayMetrics)
}

fun Float.sp2px(context: Context) : Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,this, context.resources.displayMetrics)
}

fun Float.dp(): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,this, appContext.resources.displayMetrics)
}

fun Int.dp(): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,this.toFloat(), appContext.resources.displayMetrics)
}

fun Float.format(digits: Int=1): String {
    return String.format("%.${digits}f",this)
}

fun Float.sp(): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,this, appContext.resources.displayMetrics)
}

fun Int.sp(): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,this.toFloat(), appContext.resources.displayMetrics)
}