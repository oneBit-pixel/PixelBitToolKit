package com.onBit.pixelDemo.ui.activity

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupMenu
import androidx.core.view.isVisible
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.LogUtils
import com.onBit.PixelBitToolKit.databinding.ActivityRecyclewBinding
import com.onBit.PixelBitToolKit.databinding.LayoutRvBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.lib_base.base.BaseApplication
import com.onBit.lib_base.base.utils.PopUpWindowTools
import com.onBit.lib_base.base.utils.PopUpWindowTools.showAsDropByLeft
import com.onBit.lib_base.base.utils.PopUpWindowTools.showAsDropByRight
import com.onBit.lib_base.base.utils.PopUpWindowTools.showAsUp
import com.onBit.pixelDemo.MyApp


class RecyclewActivity : BaseActivity<ActivityRecyclewBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityRecyclewBinding
        get() = ActivityRecyclewBinding::inflate

    override fun isFullScreen(): Boolean {
        return true
    }

    private val a by lazy(LazyThreadSafetyMode.PUBLICATION) { }
    fun test2(int: Int): Boolean {
        return false
    }

    private val myMenu by lazy { PopupMenu(this, mBinding.button2) }
    private val popupWindow by lazy {
        PopUpWindowTools.buildCustomPopUpWindow(
            this@RecyclewActivity,
            LayoutRvBinding.inflate(layoutInflater).root
        )
    }


    @SuppressLint("ResourceType")
    override fun initView() {
        super.initView()
        mBinding.button2.setOnClickListener {
            mBinding.apply {
                button1.isVisible=true
                button3.isVisible=true
            }
        }
        mBinding.button3.setOnClickListener {
            mBinding.button3.isVisible=false
        }
        mBinding.button1.setOnClickListener {
            mBinding.button1.isVisible=false
        }
    }


}

