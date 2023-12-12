package com.onBit.pixelDemo.ui.activity

import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.LogUtils
import com.onBit.PixelBitToolKit.databinding.ActivityThreadBinding
import com.onBit.lib_base.base.BaseActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.future.future
import kotlinx.coroutines.launch
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class ThreadActivity : BaseActivity<ActivityThreadBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityThreadBinding
        get() = ActivityThreadBinding::inflate

    val callback = {
        LogUtils.d("callback")
    }

    val task = {
        LogUtils.d("task")
        callback.invoke()
    }

    val list = listOf(
        "1这是==>",
        "2这是==>",
        "3这是==>",
        "4这是==>",
        "5这是==>",
    )

    @Volatile
    var a = 1

    fun bitmapFuture(url: String): Future<String> {
        val executor = Executors.newCachedThreadPool()
        return executor.submit<String> {
            a += 1
            LogUtils.d("a==>${a}")
            url
        }
    }

    fun bitmapCompletableFuture(str: String): CompletableFuture<String> =
        CompletableFuture.supplyAsync {
            a += 1
            LogUtils.d("a==>${a}")
            "${a}"
        }

    override fun initView() {
        super.initView()
        val arrayListOf = arrayListOf(1, 2, 3, 4)
        var isTrue = false
        lifecycleScope.launch {
            val let = list.map {
                bitmapCompletableFuture(it)
            }.let { futurelist ->
                val toTypedArray = futurelist.toTypedArray()
                CompletableFuture.allOf(*toTypedArray)
                    .thenApply {
                        futurelist.map {
                            it.get()
                        }
                    }
            }
            val list = let.get()

            LogUtils.d("执行完毕==>${list}")

        }

    }

}