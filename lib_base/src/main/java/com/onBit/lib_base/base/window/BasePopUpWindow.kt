package com.onBit.lib_base.base.window

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.onBit.lib_base.R
import com.onBit.lib_base.base.window.dao.WindowDao

abstract class BasePopUpWindow<VB : ViewBinding>(
    private val context: Context
) : DefaultLifecycleObserver, WindowDao {

    abstract val bindingInflater: (LayoutInflater) -> VB

    private var isCancelAble = false

    //弹出动画
    private var animation = -1

    protected open val mBinding by lazy {
        bindingInflater.invoke(LayoutInflater.from(context))
    }


    open val popupWindow by lazy {
        PopupWindow(context).apply {
            contentView = mBinding.root
        }
    }

    init {
        if (context is LifecycleOwner) {
            context.lifecycle.addObserver(this)
        }
    }


    override fun show(view: View, xoff: Int, yoff: Int) {
        popupWindow.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            animationStyle = animation
            popupWindow.isFocusable = isCancelAble
            showAsDropDown(view, xoff, yoff)
        }

        initView()
        initListener()
    }

    override fun setAnimationStyle(animate: Int) {
        this.animation = animate
    }

    override fun setCancelable(b: Boolean) {
        this.isCancelAble = b
    }

    override fun dismiss() {
        popupWindow.dismiss()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismiss()
        owner.lifecycle.removeObserver(this)
    }


}