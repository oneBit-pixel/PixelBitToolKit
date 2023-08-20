package com.onBit.pixelDemo.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.graphics.drawable.ShapeDrawable
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout.LayoutParams
import com.hjq.shape.builder.ShapeDrawableBuilder
import com.onBit.PixelBitToolKit.databinding.ActivityMainBinding
import com.onBit.PixelBitToolKit.databinding.LayoutViewgroupBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.pixelDemo.ui.dialog.DialogDemo
import com.onBit.pixelDemo.ui.viewGroup.TestFrameLayout
import com.tbruyelle.rxpermissions3.RxPermissions

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    private val dialog by lazy {
        DialogDemo(this)
    }
    private var windowManager: WindowManager? = null
    private var floatingView: View? = null

    override fun initView() {
        super.initView()
        if (!hasOverLayPermission(this)) {
            val intent =
                Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
            startActivityForResult(intent, 100)
        }
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

        mBinding.apply {
            dialogBtn.setOnClickListener {
                setFloatView()
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