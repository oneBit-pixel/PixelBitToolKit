package com.onBit.lib_base.base.utils

import android.content.Context
import com.blankj.utilcode.util.ActivityUtils

/**
 * 窗口工具类
 */
object WindowUtils {
    //设置窗口透明度
    fun setAlpha(context: Context, alpha: Float) {
        ActivityUtils.getActivityByContext(context)?.apply {
            window.apply {
                val newLp = attributes.apply {
                    this.alpha = alpha
                }
                attributes = newLp
            }
        }
    }
}