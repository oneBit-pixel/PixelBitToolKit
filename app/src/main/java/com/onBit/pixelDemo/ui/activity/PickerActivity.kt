package com.onBit.pixelDemo.ui.activity

import android.content.ClipData
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import com.blankj.utilcode.util.LogUtils
import com.onBit.PixelBitToolKit.R
import com.onBit.PixelBitToolKit.databinding.ActivityPickerBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.pixelDemo.ui.floatview.FloatView
import com.onBit.pixelDemo.ui.viewGroup.TestFrameLayout
import com.xuexiang.xfloatview.permission.FloatWindowPermission

class PickerActivity : BaseActivity<ActivityPickerBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityPickerBinding
        get() = ActivityPickerBinding::inflate

    override fun initView() {
        super.initView()
        mBinding.apply {
            container.addView(
                TestFrameLayout(this@PickerActivity).apply {
                    setOnLongClickListener { v ->

                        LogUtils.d("长安控件")
                        val data = ClipData.newPlainText("", "")
                        val shadowBuilder = View.DragShadowBuilder(v)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            v.startDragAndDrop(data, shadowBuilder, v, 0)
                        }

                        true
                    }

                    setOnDragListener{
                            v,event->

                        when (event.action) {
                            DragEvent.ACTION_DRAG_STARTED -> {
                                LogUtils.d("开始拖拽..")
                                true
                            }
                            DragEvent.ACTION_DROP -> {
                                // 可以在此处处理拖拽释放的逻辑
                                true
                            }
                            DragEvent.ACTION_DRAG_LOCATION->{

                                true
                            }
                            else -> false

                        }
                    }
                }
            )


        }

    }
}