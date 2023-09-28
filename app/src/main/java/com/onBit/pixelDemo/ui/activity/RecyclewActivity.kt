package com.onBit.pixelDemo.ui.activity

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.green
import com.blankj.utilcode.util.LogUtils
import com.example.lib_view.view.LedView
import com.onBit.PixelBitToolKit.databinding.ActivityRecyclewBinding
import com.onBit.lib_base.base.BaseActivity
import com.orhanobut.hawk.Hawk
import com.example.lib_view.R

class RecyclewActivity : BaseActivity<ActivityRecyclewBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityRecyclewBinding
        get() = ActivityRecyclewBinding::inflate

    override fun isFullScreen(): Boolean {
        return true
    }


    override fun initView() {
        super.initView()
        val colorList = mutableListOf<Int>()


// 添加Android内置颜色资源到列表
        colorList.add(Color.BLUE)  // 蓝色
        colorList.add(Color.GREEN) // 绿色
        colorList.add(Color.RED)   // 红色

        val drawableToBitmap = drawableToBitmap(R.drawable.mp_hourglass)
        LogUtils.d("drawableToBitmap==>${drawableToBitmap}")

        mBinding

    }



    fun drawableToBitmap( drawableId: Int): Bitmap? {
        val drawable = ContextCompat.getDrawable(this, drawableId)
        if (drawable != null) {
            val bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            return bitmap
        }
        return null
    }


}