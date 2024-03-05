package com.onBit.pixelDemo

import android.app.Application
import com.onBit.PixelBitToolKit.BuildConfig
import com.onBit.lib_base.base.BaseApplication
import com.onBit.lib_base.base.init.appContext
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : BaseApplication() {
    override fun initiaLize() {
        BuildConfig.APPLICATION_ID
    }
}