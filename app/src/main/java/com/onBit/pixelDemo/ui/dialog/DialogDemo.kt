package com.onBit.pixelDemo.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import com.onBit.PixelBitToolKit.databinding.LayoutDialogBinding
import com.onBit.lib_base.base.dialog.BaseDialog

class DialogDemo(context: Context) : BaseDialog<LayoutDialogBinding>(context) {
    override val bindingInflater: (LayoutInflater) -> LayoutDialogBinding =
        LayoutDialogBinding::inflate

    override fun initView() {

    }

    override fun initListener() {
        mBinding.apply {
            progress.setOnClickListener {
                dismissDialog()
            }
        }
    }


}