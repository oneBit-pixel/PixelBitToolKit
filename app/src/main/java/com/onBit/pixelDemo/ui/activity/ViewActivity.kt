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
    override val bindingInflater: (LayoutInflater) -> ActivityViewBinding
        get() = ActivityViewBinding::inflate

    override fun isImTheme(): Boolean = true


    private val dialog by lazy {
        DialogDemo(this)
    }

    private val popupWindow by lazy {
        MPopUpWindow(this).apply {
            setCancelable(true)
        }
    }
    override val viewModel: Class<MViewModel>
        get() = MViewModel::class.java

    private val job by lazy {
        lifecycleScope.launch(Dispatchers.IO) {
            simple().collect { value ->
                LogUtils.d("test==>$value")
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility", "Recycle")
    override fun initView() {
        super.initView()

        mBinding.saveBtn.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                RxPermissions(this)
                    .request(Manifest.permission.READ_MEDIA_IMAGES)
                    .subscribe()
            }else{
                RxPermissions(this)
                    .request(Manifest.permission.READ_EXTERNAL_STORAGE)
                    .subscribe()
            }
        }

        mBinding.button.setOnClickListener {
            if (job.isCancelled) {
                job.start()
            } else {
                job.cancel()
            }
            
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        permissions.forEachIndexed { index, permission ->
            LogUtils.d("$permission${
                grantResults[index] == PackageManager.PERMISSION_GRANTED
            }")
        }

    }


    private fun simple(): Flow<Int> = flow {
        for (i in 1..100) {
            delay(1000)
            emit(i)
        }
    }


}