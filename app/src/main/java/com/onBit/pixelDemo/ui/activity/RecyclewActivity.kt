package com.onBit.pixelDemo.ui.activity

import android.animation.AnimatorInflater
import android.annotation.SuppressLint
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
import androidx.core.view.isVisible
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

    @SuppressLint("ResourceType")
    override fun initView() {
        super.initView()
        val fadeInAnimation = AnimatorInflater.loadAnimator(this, com.xuexiang.xui.R.animator.fragment_fade_enter)
        val fadeOutAnimation = AnimatorInflater.loadAnimator(this, com.xuexiang.xui.R.animator.fragment_close_exit)
        mBinding.button2.setOnClickListener {
            mBinding.button.isVisible = !mBinding.button.isVisible
        }
        }
    }






class A {

}
