package com.onBit.pixelDemo.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import com.onBit.PixelBitToolKit.databinding.DialogTestBinding
import com.onBit.lib_base.base.dialog.BaseDialog

class MyDialog(context:Context):BaseDialog<DialogTestBinding>(context) {
    override val bindingInflater: (LayoutInflater) -> DialogTestBinding
        get() = DialogTestBinding::inflate

    override fun initView() {
        mBinding.diloagTv.text="4321"
        mBinding.diloagTv.setOnClickListener {
            mBinding.diloagTv.text="123"
        }
    }

    override fun initListener() {

    }

    override fun initEvent() {

    }
}