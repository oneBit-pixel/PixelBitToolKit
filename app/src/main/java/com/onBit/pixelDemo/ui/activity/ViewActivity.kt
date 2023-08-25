package com.onBit.pixelDemo.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.onBit.PixelBitToolKit.databinding.ActivityViewBinding
import com.onBit.lib_base.base.BaseActivity

class ViewActivity : BaseActivity<ActivityViewBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityViewBinding
        get() = ActivityViewBinding::inflate

    override fun isImTheme(): Boolean = true


    @SuppressLint("ClickableViewAccessibility", "Recycle")
    override fun initView() {
        super.initView()


        val apply = mBinding.apply {
            button.setOnClickListener {
                startActivity(
                    Intent(
                        this@ViewActivity, MainActivity::class.java
                    )
                )
            }

        }

    }


}