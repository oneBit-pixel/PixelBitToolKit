package com.onBit.pixelDemo.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.blankj.utilcode.util.LogUtils
import com.onBit.PixelBitToolKit.databinding.LayoutRv2Binding
import com.onBit.PixelBitToolKit.databinding.LayoutRvBinding
import com.onBit.lib_base.base.adapter.BaseAdapter

//创建多样性布局
class TestAdapter : BaseAdapter<String, LayoutRvBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> LayoutRvBinding
        get() = LayoutRvBinding::inflate

    override fun onBind(binding: LayoutRvBinding, item: String, position: Int) {
        binding.textView.text = position.toString()
    }

}