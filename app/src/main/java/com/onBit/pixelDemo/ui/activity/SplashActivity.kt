package com.onBit.pixelDemo.ui.activity

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import com.onBit.PixelBitToolKit.R
import com.onBit.PixelBitToolKit.databinding.ActivitySplashBinding
import com.onBit.lib_base.base.BaseSplashActivity


class SplashActivity : BaseSplashActivity<ActivitySplashBinding>() {
    override fun onFinishTime() {

    }

    override fun onProgressUpdate(percent: Int) {

    }

    override val bindingInflater: (LayoutInflater) -> ActivitySplashBinding
        get() = ActivitySplashBinding::inflate

    override fun initView() {
        super.initView()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    )
        }

        mBinding.nav.apply {
            itemIconTintList = null //去除遮罩层 显示图片
            itemBackground = null //去除水波效果
            selectedItemId=R.id.hot
        }
    }
}