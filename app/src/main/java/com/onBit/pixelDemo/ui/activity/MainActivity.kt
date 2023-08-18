package com.onBit.pixelDemo.ui.activity

import android.view.LayoutInflater
import com.onBit.PixelBitToolKit.databinding.ActivityMainBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.pixelDemo.ui.dialog.DialogDemo

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    private val dialog by lazy {
        DialogDemo(this)
    }


    override fun initListener() {
        super.initListener()

        mBinding.apply {
            dialogBtn.setOnClickListener {
                dialog.showDialog()
            }
        }

    }

}