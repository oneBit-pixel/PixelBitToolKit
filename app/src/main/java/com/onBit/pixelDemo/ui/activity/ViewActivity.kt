package com.onBit.pixelDemo.ui.activity

import android.app.usage.NetworkStats
import android.app.usage.NetworkStatsManager
import android.app.usage.UsageStatsManager
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.text.format.Formatter
import android.view.LayoutInflater
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.TimeUtils
import com.google.android.gms.common.wrappers.PackageManagerWrapper
import com.onBit.PixelBitToolKit.databinding.ActivityViewBinding
import com.onBit.lib_base.base.BaseViewModelActivity
import com.onBit.pixelDemo.utls.permission.UsageAccessPermissionUtils
import com.onBit.pixelDemo.viewmodel.MViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit
import java.util.logging.SimpleFormatter


class ViewActivity : BaseViewModelActivity<ActivityViewBinding, MViewModel>(
) {
    override val viewModel: Class<MViewModel>
        get() = MViewModel::class.java
    override val bindingInflater: (LayoutInflater) -> ActivityViewBinding
        get() = ActivityViewBinding::inflate


    override fun initView() {
        super.initView()

        mBinding.run {
            root.setOnTouchListener { v, event ->
                return@setOnTouchListener true
            }

        }
    }


}