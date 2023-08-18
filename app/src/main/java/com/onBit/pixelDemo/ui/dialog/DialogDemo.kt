package com.onBit.pixelDemo.ui.dialog

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import com.onBit.PixelBitToolKit.databinding.DialogTestBinding
import com.onBit.lib_base.base.BaseDialog

class DialogDemo(context: Context) : BaseDialog<DialogTestBinding>(context) {
    override val bindingInflater: (LayoutInflater) -> DialogTestBinding = DialogTestBinding::inflate

    override fun initView() {
        super.initView()
        mBinding.diloagTv.text = "666"

    }

    override fun initListener() {
        super.initListener()
        mBinding.apply {
            diloagTv.setOnClickListener {
                dismissDialog()
            }
        }
    }



}