package com.onBit.lib_base.base

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding


/**
 * 快速创建自定义dialog 的基类
 *
 * @constructor
 * 构造器
 *
 * @param context 上下文
 * @param cancelable 是否可以取消
 */
abstract class BaseDialog2<VB : ViewBinding> constructor(
    context: Context,
    private val cancelable: Boolean = true

) : AlertDialog(context), DefaultLifecycleObserver {

    abstract val bindingInflater: (LayoutInflater) -> VB

    protected open val mBinding by lazy {
        bindingInflater.invoke(layoutInflater)
    }

    init {



        showDialog()
        if (context is LifecycleOwner) {
            context.lifecycle.addObserver(this)
        }
    }

    //避免内存泄露
    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismiss()
    }

    open fun showDialog() {
        setView(mBinding.root)
        setCancelable(cancelable)
        setCanceledOnTouchOutside(true)
        show()

        window?.setBackgroundDrawable(null)
        initView()
        initListener()
    }

    protected open fun initListener() {

    }

    protected open fun initView() {

    }

}