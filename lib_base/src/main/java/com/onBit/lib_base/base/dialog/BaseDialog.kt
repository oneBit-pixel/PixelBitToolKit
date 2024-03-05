package com.onBit.lib_base.base.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.onBit.lib_base.R

/**
 * 快速自定义dialog 基类
 *
 * @param context 上下文
 * @return
 * @author zhangxuyang
 * @create 2023/8/17
 **/

abstract class BaseDialog<VB : ViewBinding>(
    private val context: Context,themeResId:Int=R.style.RoundDialogTheme
) :Dialog(context, themeResId), DefaultLifecycleObserver{

    abstract val bindingInflater: (LayoutInflater) -> VB

    protected open val mBinding by lazy {
        bindingInflater.invoke(LayoutInflater.from(context))
    }

    init {
        //绑定生命周期
        if (context is LifecycleOwner) {
            context.lifecycle.addObserver(this)
        }
    }

    override fun show() {
        super.show()
        initView()
        initListener()
        initEvent()
    }
    abstract fun initView()
    abstract fun initListener()
    abstract fun initEvent()


    override fun onCreate(savedInstanceState: Bundle?) {
        super<Dialog>.onCreate(savedInstanceState)
        setContentView(mBinding.root)
    }

    override fun cancel() {
        super.cancel()
        dismiss()
    }


    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismiss()
        owner.lifecycle.removeObserver(this)
    }
}