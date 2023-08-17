package com.onBit.pixelDemo.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.onBit.PixelBitToolKit.R
import com.onBit.PixelBitToolKit.databinding.ActivityMainBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.pixelDemo.ui.dialog.Mdialog

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    private val  dialog by lazy {
        Mdialog(this)
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