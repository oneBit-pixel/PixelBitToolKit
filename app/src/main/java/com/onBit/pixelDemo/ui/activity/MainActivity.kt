package com.onBit.pixelDemo.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.provider.Settings
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import androidx.core.os.bundleOf
import com.google.firebase.Firebase
import com.google.firebase.analytics.analytics
import com.onBit.PixelBitToolKit.BuildConfig
import com.onBit.PixelBitToolKit.databinding.ActivityMainBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.pixelDemo.ui.viewGroup.TestFrameLayout
import com.v2ray.ang.service.V2RayServiceManager
import java.lang.RuntimeException

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate


    private var windowManager: WindowManager? = null
    private var floatingView: View? = null


    private val dp100 by lazy {
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 100f, resources.displayMetrics
        ).toInt()
    }

    override fun initView() {
        super.initView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            setFloatView()
        }
    }

    fun hasOverLayPermission(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Settings.canDrawOverlays(this)
        } else {
            true
        }
    }

    override fun initListener() {
        super.initListener()
        Firebase.analytics.logEvent("MainActivity",bundleOf().apply {
            putString("testView","View")
        })
        mBinding.apply {
            dialogBtn.setOnClickListener {
                Firebase.analytics.logEvent("MainActivity",bundleOf().apply {
                    putInt("testInt",1)
                })

            }
        }

    }

    private var initialX: Int = 0
    private var initialY: Int = 0
    private var initialTouchX: Float = 0f
    private var initialTouchY: Float = 0f

    @SuppressLint("ClickableViewAccessibility")
    private fun setFloatView() {
        // 初始化视图
        floatingView = TestFrameLayout(this)

        // 定义布局参数
        val windowType = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            WindowManager.LayoutParams.TYPE_PHONE
        }

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            windowType,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        // 获取WindowManager服务
        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        // 添加视图到窗口
        windowManager?.addView(floatingView, params)

        floatingView?.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // 记录初始位置
                    initialX = params.x
                    initialY = params.y
                    initialTouchX = event.rawX
                    initialTouchY = event.rawY
                }

                MotionEvent.ACTION_MOVE -> {
                    // 计算移动的距离并更新窗口位置
                    params.x = initialX + (event.rawX - initialTouchX).toInt()
                    params.y = initialY + (event.rawY - initialTouchY).toInt()
                    windowManager?.updateViewLayout(v, params)
                }
            }

            true
        }
    }

}