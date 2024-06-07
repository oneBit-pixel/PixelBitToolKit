package com.onBit.lib_base.base.window

import android.graphics.Color
import android.view.Window
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

/**
 * 开启沉浸模式
 */
fun setImmersed(window: Window, isLight: Boolean = true) {
    window.statusBarColor = Color.TRANSPARENT
    window.navigationBarColor = Color.TRANSPARENT
    WindowCompat.getInsetsController(window, window.decorView).run {
        this.isAppearanceLightStatusBars = isLight
    }
    ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { v, insets: WindowInsetsCompat ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        insets
    }
}