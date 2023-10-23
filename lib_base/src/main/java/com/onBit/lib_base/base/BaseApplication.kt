package com.onBit.lib_base.base

import android.app.Application
import com.onBit.lib_base.base.init.appContext

abstract class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
        initiaLize()
    }

    override fun onTerminate() {
        super.onTerminate()
        //进程结束

    }



    abstract fun initiaLize()


}

