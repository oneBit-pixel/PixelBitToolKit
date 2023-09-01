package com.onBit.pixelDemo.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.onBit.PixelBitToolKit.databinding.ActivityViewBinding
import com.onBit.PixelBitToolKit.databinding.LayoutDialogBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.pixelDemo.ui.dialog.DialogDemo

class ViewActivity : BaseActivity<ActivityViewBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityViewBinding
        get() = ActivityViewBinding::inflate

    override fun isImTheme(): Boolean = true


    private val dialog by lazy {
        DialogDemo(this)
    }


    @SuppressLint("ClickableViewAccessibility", "Recycle")
    override fun initView() {
        super.initView()


        mBinding.button.setOnClickListener {
            dialog.showDialog()
        }


    }


}