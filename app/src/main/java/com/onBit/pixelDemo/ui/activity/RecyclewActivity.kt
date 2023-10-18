package com.onBit.pixelDemo.ui.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Shader
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.net.Uri
import android.provider.Settings
import android.view.LayoutInflater
import android.widget.PopupWindow
import androidx.camera.core.processing.SurfaceProcessorNode.In
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.onBit.PixelBitToolKit.R
import com.onBit.PixelBitToolKit.databinding.ActivityRecyclewBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.pixelDemo.ui.floatview.FloatView
import com.onBit.pixelDemo.ui.fragment.MyFragment


class RecyclewActivity : BaseActivity<ActivityRecyclewBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityRecyclewBinding
        get() = ActivityRecyclewBinding::inflate

    override fun isFullScreen(): Boolean {
        return true
    }

    private val floatView by lazy { FloatView(this) }
    override fun isHideNavigation(): Boolean {
        return true
    }

    private val adapter by lazy {
        object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 10
            }

            override fun createFragment(position: Int): Fragment {
                return MyFragment(position)
            }

        }
    }

    fun a(
        num: Int,
        test: (Int) -> Boolean
    ): Int {
        if (test(1)) {

        }
        return 1
    }

    fun test2(int: Int): Boolean {
        return false
    }

    override fun initView() {
        super.initView()


       PopupWindow(this).apply {
           animationStyle
       }


    }


    fun drawableToBitmap(drawableId: Int): Bitmap? {
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