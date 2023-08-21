package com.onBit.pixelDemo.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.onBit.PixelBitToolKit.R
import com.onBit.PixelBitToolKit.databinding.ActivityPickerBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.pixelDemo.ui.floatview.FloatView
import com.xuexiang.xfloatview.permission.FloatWindowPermission

class PickerActivity : BaseActivity<ActivityPickerBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityPickerBinding
        get() = ActivityPickerBinding::inflate

    override fun initView() {
        super.initView()
        FloatWindowPermission.getInstance().applyFloatWindowPermission(this)
        FloatView(this).show()

    }
}