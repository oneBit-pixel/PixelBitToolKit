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
class TestAdapter : BaseAdapter<String, ViewBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> LayoutRvBinding
        get() = LayoutRvBinding::inflate

    override fun onBind(binding: ViewBinding, item: String, position: Int) {
        if (binding is LayoutRvBinding) {
            binding.textView.text = position.toString()
        } else if (binding is LayoutRv2Binding) {
            binding.apply {
                shapeTv.setOnClickListener {
                    shapeTv.shapeDrawableBuilder.apply {
                        solidColor = Color.RED
                        buildBackgroundDrawable()
                    }
                }
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<ViewBinding> {
        LogUtils.d("viewType==>$viewType")
        if (viewType < 3) {
            val inflate =
                LayoutRv2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(inflate)
        } else {

            return super.onCreateViewHolder(parent, viewType)
        }
    }

}