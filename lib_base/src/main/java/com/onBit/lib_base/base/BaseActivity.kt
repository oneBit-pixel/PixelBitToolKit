package com.onBit.lib_base.base

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.palette.graphics.Palette
import androidx.viewbinding.ViewBinding
import com.blankj.utilcode.util.LogUtils
import com.google.android.material.color.utilities.CorePalette

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected abstract val bindingInflater: (LayoutInflater) -> VB

    protected open val mBinding by lazy {
        bindingInflater.invoke(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        if (isImTheme()) {
            setImTheme()
        }

        if (isFullScreen()) {
            setFullScreen()
        }

        initView()
        initListener()
        initEvent()
    }

    private fun setFullScreen() {
        // 如果你的导航栏颜色为白色，确保导航栏上的图标和文字为深色
        val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY


        } else {

            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
        window.decorView.systemUiVisibility = flag


    }

    //沉浸式主题
    protected open fun isFullScreen(): Boolean = false


    //沉浸式主题
    private fun setImTheme() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        //默认黑色 可以后面自己改
        window.navigationBarColor = Color.TRANSPARENT
        window.statusBarColor = Color.TRANSPARENT


        // 如果你的导航栏颜色为白色，确保导航栏上的图标和文字为深色
        window.decorView.systemUiVisibility = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
        val background = mBinding.root.background
        if (background is ColorDrawable) {
            setStatusLight(
                background.color
            )
        } else if (background is BitmapDrawable) {
            val bitmap = background.bitmap
            Palette.from(bitmap)
                .setRegion(0, 0, bitmap.width, 100)
                .generate { palette ->
                    palette?.dominantSwatch?.rgb?.also { color ->
                        setStatusLight(color)
                    }
                }
        }
    }

    protected open fun setStatusLight(color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val flags = window.decorView.systemUiVisibility
            window.decorView.systemUiVisibility = if (isColorDark(color)) {
                //如果是深色则去掉 高亮
                flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            } else {
                //如果不是深色 则添加高亮
                flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }

    }


    protected open fun isColorDark(color: Int): Boolean {
        val darkness =
            1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255

        return darkness >= 0.5
    }


    protected open fun isImTheme(): Boolean = true


    protected open fun initListener() {

    }

    protected open fun initEvent() {

    }

    protected open fun initView() {

    }

}