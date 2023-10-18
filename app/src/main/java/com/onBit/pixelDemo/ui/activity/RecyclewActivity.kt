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

    private val a by lazy(LazyThreadSafetyMode.PUBLICATION) {  }
    fun test2(int: Int): Boolean {
        return false
    }

    override fun initView() {
        super.initView()
        for (c in 'a'..'e') {
            println("c==>$c")
        }
        "abcdefg".filter { c ->
            c in 'a'..'d'
        }
    }

    infix fun says(name: String) {
        println("1 says:$name")
    }

}

class Person(val name: String) {
    infix fun says(message: String) {
        println("$name says: $message")
    }
}

class A {

}
