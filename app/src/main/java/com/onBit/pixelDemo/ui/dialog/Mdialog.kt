package com.onBit.pixelDemo.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import com.onBit.PixelBitToolKit.databinding.DialogTestBinding
import com.onBit.lib_base.base.BaseDialog2

class Mdialog(context: Context, cancelable: Boolean = true) :
    BaseDialog2<DialogTestBinding>(context,cancelable) {
    override val bindingInflater: (LayoutInflater) -> DialogTestBinding
        get() = DialogTestBinding::inflate

    override fun initView() {
        super.initView()
    }

    override fun initListener() {
        super.initListener()

        mBinding.apply {
            diloagTv.setOnClickListener {
                dismiss()
            }
        }
    }


}