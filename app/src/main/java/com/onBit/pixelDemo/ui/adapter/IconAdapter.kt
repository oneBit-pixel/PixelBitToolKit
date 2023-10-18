package com.onBit.pixelDemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.onBit.PixelBitToolKit.databinding.AdapterIconItemBinding
import com.onBit.lib_base.base.adapter.BaseAdapter

class IconAdapter : BaseAdapter<String, AdapterIconItemBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> AdapterIconItemBinding
        get() = AdapterIconItemBinding::inflate

    override fun onBind(binding: AdapterIconItemBinding, item: String, position: Int) {

    }
}
