package com.onBit.pixelDemo.ui.activity

import android.view.LayoutInflater
import com.onBit.PixelBitToolKit.databinding.ActivityViewBinding
import com.onBit.lib_base.base.BaseViewModelActivity
import com.onBit.pixelDemo.viewmodel.MViewModel

class ViewActivity : BaseViewModelActivity<ActivityViewBinding, MViewModel>(
) {
    override val viewModel: Class<MViewModel>
        get() = MViewModel::class.java
    override val bindingInflater: (LayoutInflater) -> ActivityViewBinding
        get() = ActivityViewBinding::inflate


    override fun initView() {
        super.initView()
        mBinding.apply {
            val mutableListOf = mutableListOf<String>()
            for (i in 1..100){
                mutableListOf.add("")
            }
        }
    }

}