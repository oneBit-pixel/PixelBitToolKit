package com.onBit.pixelDemo.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.LogUtils
import com.hjq.permissions.XXPermissions
import com.onBit.PixelBitToolKit.databinding.ActivityViewBinding
import com.onBit.PixelBitToolKit.databinding.LayoutDialogBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.lib_base.base.BaseViewModelActivity
import com.onBit.pixelDemo.ui.adapter.TestAdapter
import com.onBit.pixelDemo.ui.dialog.DialogDemo
import com.onBit.pixelDemo.ui.window.MPopUpWindow
import com.onBit.pixelDemo.viewmodel.MViewModel
import com.tbruyelle.rxpermissions3.RxPermissions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.jetbrains.annotations.TestOnly

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
            recyclerview.adapter=TestAdapter().apply {
                setList(mutableListOf)
            }
        }
    }

}