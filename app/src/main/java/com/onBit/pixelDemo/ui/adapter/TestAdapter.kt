package com.onBit.pixelDemo.ui.adapter

import android.view.LayoutInflater
import com.onBit.PixelBitToolKit.databinding.LayoutRvBinding
import com.onBit.lib_base.base.adapter.BaseAdapter

class TestAdapter : BaseAdapter<String, LayoutRvBinding>() {
    override val bindingInflater: (LayoutInflater) -> LayoutRvBinding
        get() = LayoutRvBinding::inflate

    override fun onBind(binding: LayoutRvBinding, item: String, position: Int) {
        binding.textView.text = item.toString()
    }
}