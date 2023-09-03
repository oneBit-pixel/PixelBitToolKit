package com.onBit.pixelDemo.ui.window

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.onBit.PixelBitToolKit.R
import com.onBit.PixelBitToolKit.databinding.LayoutPopWindowBinding
import com.onBit.lib_base.base.window.BasePopUpWindow
import com.onBit.pixelDemo.ui.adapter.TestAdapter

class MPopUpWindow(
    private val context: Context
) : BasePopUpWindow<LayoutPopWindowBinding>(context) {
    override val bindingInflater: (LayoutInflater) -> LayoutPopWindowBinding
        get() = LayoutPopWindowBinding::inflate

    override fun initView() {
        mBinding.apply {
            recyclerview.adapter = TestAdapter().apply {
                setList(
                    context.resources.getStringArray(R.array.strings).toList()
                )
            }
        }
    }


    override fun initListener() {

    }
}