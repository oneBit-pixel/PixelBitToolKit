package com.onBit.pixelDemo.ui.fragment

import com.onBit.PixelBitToolKit.databinding.FragmentMyBinding
import com.onBit.lib_base.base.BaseFragment

class MyFragment(val position: Int) : BaseFragment<FragmentMyBinding>(
    FragmentMyBinding::inflate
) {

    override fun initView() {
        super.initView()
        mBinding.textView2.text = "fragment${position}"
    }


}