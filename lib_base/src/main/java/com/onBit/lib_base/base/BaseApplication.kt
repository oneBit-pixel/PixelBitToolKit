package com.onBit.lib_base.base

import android.app.Application
import com.onBit.lib_base.base.init.appContext
import kotlinx.coroutines.flow.flow

abstract class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
        initiaLize()
    }

    override fun onTerminate() {
        super.onTerminate()


    }



    abstract fun initiaLize()


}

