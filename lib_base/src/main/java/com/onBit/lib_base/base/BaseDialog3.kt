package com.onBit.lib_base.base

import android.app.ActionBar.LayoutParams
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding

/**
 * 快速自定义dialog 基类
 *
 * @param context 上下文
 * @return
 * @author zhangxuyang
 * @create 2023/8/17
 **/

abstract class BaseDialog3<VB : ViewBinding>(

    val context: Context
) : DefaultLifecycleObserver {

    abstract val bindingInflater: (LayoutInflater) -> VB

    protected open val mBinding by lazy {
        bindingInflater.invoke(LayoutInflater.from(context))
    }

    private val dialog by lazy {
        AlertDialog.Builder(context)
            .setView(mBinding.root)
            .create()
    }


    init {
        //绑定生命周期
        if (context is LifecycleOwner) {
            context.lifecycle.addObserver(this)
        }

    }

    fun showDialog() {
        dialog.apply {
            setCancelable(true)
            setCanceledOnTouchOutside(true)
            show()


            mBinding.root.measure(
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            )

            val measuredWidth = mBinding.root.measuredWidth

            window?.attributes?.apply {
                width = measuredWidth
            }.also {
                window?.attributes = it
            }

            window?.setBackgroundDrawable(null)
//            window?.setGravity(Gravity.BOTTOM)
            setOnCancelListener {
                dismiss()
            }
        }



        initView()
        initListener()
    }

    fun dismissDialog() {
        dialog.dismiss()
    }

    protected open fun initView() {

    }

    protected open fun initListener() {

    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
//        dialog.dismiss()
        owner.lifecycle.removeObserver(this)
    }
}