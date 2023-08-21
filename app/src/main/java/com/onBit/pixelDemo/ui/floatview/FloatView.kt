package com.onBit.pixelDemo.ui.floatview

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.onBit.PixelBitToolKit.databinding.LayoutViewgroupBinding
import com.xuexiang.xfloatview.XFloatView

class FloatView(context: Context) : XFloatView<LayoutViewgroupBinding>(context) {
    override fun canMoveOrTouch(): Boolean {
        return true
    }

    override fun initFloatView() {
        binding.moveView.setBackgroundColor(Color.BLUE)
    }

    override fun initListener() {

    }

    override fun isAdsorbView(): Boolean {
        return false
    }

    override fun bindView(layoutInflater: LayoutInflater): LayoutViewgroupBinding {
        return LayoutViewgroupBinding.inflate(layoutInflater)
    }
}