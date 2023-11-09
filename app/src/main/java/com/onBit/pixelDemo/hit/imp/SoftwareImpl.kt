package com.onBit.pixelDemo.hit.imp

import com.blankj.utilcode.util.LogUtils
import com.onBit.pixelDemo.hit.ISoftware

class SoftwareImpl :ISoftware {
    override fun printName() {
        LogUtils.d("SoftwareImpl")
    }
}