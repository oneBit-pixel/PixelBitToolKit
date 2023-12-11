package com.onBit.pixelDemo.ui.activity

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
import com.blankj.utilcode.util.LogUtils
import com.example.lib_view.clockView.ClockView
import com.onBit.PixelBitToolKit.R
import com.onBit.PixelBitToolKit.databinding.ActivityClockBinding
import com.onBit.lib_base.base.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Clock

class ClockActivity : BaseActivity<ActivityClockBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityClockBinding
        get() = ActivityClockBinding::inflate

    override fun initView() {
        super.initView()

        val clockView = ClockView(this@ClockActivity)
        clockView.measure(
            View.MeasureSpec.makeMeasureSpec(600, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(600, View.MeasureSpec.EXACTLY)
        )

//        mBinding.root.addView(clockView)
        val bitmap = Bitmap.createBitmap(
            clockView.measuredWidth,
            clockView.measuredHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        clockView.draw(canvas)

        val icon = Icon.createWithBitmap(bitmap)
        mBinding.analogClock.setDial(icon)

    }

}