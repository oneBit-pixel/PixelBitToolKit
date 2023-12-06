package com.onBit.pixelDemo.ui.activity

import android.view.LayoutInflater
import com.blankj.utilcode.util.LogUtils
import com.onBit.PixelBitToolKit.databinding.ActivityThreadBinding
import com.onBit.lib_base.base.BaseActivity
import kotlin.concurrent.thread

class ThreadActivity : BaseActivity<ActivityThreadBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityThreadBinding
        get() = ActivityThreadBinding::inflate

    val callback ={
        LogUtils.d("callback")
    }

    val task={
        LogUtils.d("task")
        callback.invoke()
    }

    override fun initView() {
        super.initView()

        LogUtils.d("A")
        thread(block = task)
        LogUtils.d("B")
    }

}