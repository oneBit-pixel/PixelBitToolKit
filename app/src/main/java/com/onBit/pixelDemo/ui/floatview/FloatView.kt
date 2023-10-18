package com.onBit.pixelDemo.ui.floatview

import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import com.blankj.utilcode.util.ScreenUtils
import com.onBit.PixelBitToolKit.databinding.LayoutEdgeviewBinding
import com.xuexiang.xfloatview.XFloatView

class FloatView(context: Context) : XFloatView<LayoutEdgeviewBinding>(context) {
    override fun canMoveOrTouch(): Boolean {
        return false
    }

    override fun initFloatView() {
        wmParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY, // 悬浮窗口类型，需要权限
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN // 标志
                    or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                    or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, // 不获取焦点
            PixelFormat.TRANSLUCENT // 透明背景
        )
        wmParams.gravity = Gravity.TOP or Gravity.START
        wmParams.height = ScreenUtils.getScreenHeight()

    }


    override fun initListener() {

    }

    override fun isAdsorbView(): Boolean {
        return true
    }

    override fun bindView(layoutInflater: LayoutInflater): LayoutEdgeviewBinding {
        return LayoutEdgeviewBinding.inflate(layoutInflater)
    }
}