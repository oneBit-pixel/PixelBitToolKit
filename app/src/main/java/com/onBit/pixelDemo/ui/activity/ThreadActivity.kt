package com.onBit.pixelDemo.ui.activity

import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.LogUtils
import com.onBit.PixelBitToolKit.databinding.ActivityThreadBinding
import com.onBit.lib_base.base.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.future.future
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class ThreadActivity : BaseActivity<ActivityThreadBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityThreadBinding
        get() = ActivityThreadBinding::inflate
    var task: Job? = null


    override fun initView() {
        super.initView()

    }

    override fun initListener() {
        super.initListener()
        mBinding.button.setOnClickListener {
            task?.cancel()
        }
    }

    suspend fun log1() {
        withContext(Dispatchers.IO) {
            delay(1000)
        }
        LogUtils.d()
    }

}