package com.onBit.lib_base.base

import android.graphics.Bitmap
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
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.ScreenUtils
import com.onBit.lib_base.base.utils.ColorUtilsEx

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected abstract val bindingInflater: (LayoutInflater) -> VB

    protected open val mBinding by lazy {
        bindingInflater.invoke(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initView()
        initListener()
        initEvent()
    }

    override fun onResume() {
        super.onResume()
    }

    //继承类可以判断是更具颜色设置
    protected open fun setTheme() {
        BarUtils.setStatusBarColor(this,Color.TRANSPARENT)
        BarUtils.transparentNavBar(this)
    }



    protected open fun setStatusLight(color: Int) {
        BarUtils.setStatusBarLightMode(this, ColorUtils.isLightColor(color))
    }


    protected open fun isImTheme(): Boolean = true


    protected open fun initListener() {

    }

    protected open fun initEvent() {

    }

    protected open fun initView() {

    }

}