package com.onBit.pixelDemo.ui.activity

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.viewpager.widget.ViewPager.DecorView
import com.blankj.utilcode.util.LogUtils
import com.onBit.PixelBitToolKit.R
import com.onBit.PixelBitToolKit.databinding.ActivityViewBinding
import com.onBit.lib_base.base.BaseActivity

class ViewActivity : BaseActivity<ActivityViewBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityViewBinding
        get() = ActivityViewBinding::inflate

    @SuppressLint("ClickableViewAccessibility", "Recycle")
    override fun initView() {
        super.initView()

        mBinding.apply {
//            container.layoutAnimation.start()
            test1.setOnClickListener {
                startActivity(
                    Intent(this@ViewActivity, MainActivity::class.java)
                )
            }


            textView.setOnClickListener {
                val dpp100 = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 100f, resources.displayMetrics
                )
                test1.animate()
                    .x(test1.x+dpp100)
                    .setDuration(3000L)
                    .start()

                test1.clearAnimation()
            }
        }


    }


}