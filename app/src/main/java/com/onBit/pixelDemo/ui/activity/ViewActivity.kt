package com.onBit.pixelDemo.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    @SuppressLint("ClickableViewAccessibility")
    override fun initView() {
        super.initView()

        mBinding.apply {

            textView.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    LogUtils.d("2134")
                    onTouchEvent(event)

                    return false
                }
            })


            textView.setOnClickListener {

                LogUtils.d("点击事件..")
            }

            test1.setOnTouchListener { v, event ->

                textView.dispatchTouchEvent(event)
              true
            }
        }


    }



}