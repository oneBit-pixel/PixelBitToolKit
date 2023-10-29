package com.onBit.pixelDemo.ui.activity

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupMenu
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.LogUtils
import com.example.studyProject.studyKotlin.Mule
import com.onBit.PixelBitToolKit.databinding.ActivityRecyclewBinding
import com.onBit.PixelBitToolKit.databinding.LayoutRvBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.lib_base.base.utils.PopUpWindowTools
import com.onBit.lib_base.base.utils.PopUpWindowTools.showAsDropByLeft
import com.onBit.lib_base.base.utils.PopUpWindowTools.showAsDropByRight
import com.onBit.lib_base.base.utils.PopUpWindowTools.showAsUp


class RecyclewActivity : BaseActivity<ActivityRecyclewBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityRecyclewBinding
        get() = ActivityRecyclewBinding::inflate

    override fun isFullScreen(): Boolean {
        return true
    }


    @SuppressLint("ResourceType")
    override fun initView() {
        super.initView()
        Mule().DonkeyC().doSomeThing()

    }


}

