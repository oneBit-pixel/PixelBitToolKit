package com.onBit.pixelDemo.ui.activity

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.WindowManager
import android.view.WindowManager.LayoutParams
import android.widget.AnalogClock
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.example.lib_view.clockView.ClockView
import com.onBit.PixelBitToolKit.R
import com.onBit.PixelBitToolKit.databinding.ActivityClockBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.pixelDemo.utls.XorUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException
import java.nio.charset.Charset
import java.time.Clock
import java.util.Base64

class ClockActivity : BaseActivity<ActivityClockBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityClockBinding
        get() = ActivityClockBinding::inflate

    @SuppressLint("NewApi")
    override fun initView() {
        super.initView()

        reqestData()

    }

    private fun reqestData() {
        val hashMap = HashMap<String, Any>()
        hashMap["header"] = XorUtils.toMap()
        hashMap["data"] = XorUtils.toData()
        val toJson = GsonUtils.toJson(hashMap)
        val okHttpClient = OkHttpClient()

        val xorJson = XorUtils.xor(
            "original", toJson.toByteArray(charset("utf-8"))
        )

        val encodeToString = Base64.getEncoder().encodeToString(xorJson)

        val requestBody = xorJson.toRequestBody("application/octet-stream".toMediaTypeOrNull())

        val request = Request.Builder()
            .header("X-Tag", "FAkSHBIVGhc=")
            .url("https://themeassdfsa.xyz/app/chat")
            .post(requestBody)
            .build()

        okHttpClient.newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    LogUtils.e("出错了==>${e.toString()}")
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val responseBody = response.body
                        LogUtils.d("responseBody==>${responseBody?.string()}")
                    }
                }

            })
    }

}