package com.onBit.pixelDemo.model

import com.blankj.utilcode.util.LogUtils
import javax.inject.Inject

class User @Inject constructor() {

    @Inject
    fun initUser(){
        LogUtils.d("initUser")
    }

    fun say(){
        LogUtils.d("6666")
    }

}