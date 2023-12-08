package com.onBit.pixelDemo.ui.window

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.onBit.PixelBitToolKit.R
import com.onBit.PixelBitToolKit.databinding.LayoutPopWindowBinding
import com.onBit.lib_base.base.window.BasePopUpWindow

class MPopUpWindow(
    private val context: Context
) : BasePopUpWindow<LayoutPopWindowBinding>(context) {
    override val bindingInflater: (LayoutInflater) -> LayoutPopWindowBinding
        get() = LayoutPopWindowBinding::inflate

    override fun initView() {
        mBinding.apply {

        }
    }


    override fun initListener() {

    }
}