package com.onBit.lib_base.base.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.onBit.lib_base.base.dialog.dao.DialogDao

/**
 * 快速自定义dialog 基类
 *
 * @param context 上下文
 * @return
 * @author zhangxuyang
 * @create 2023/8/17
 **/

abstract class BaseDialog<VB : ViewBinding>(
    private val context: Context
) : DefaultLifecycleObserver, DialogDao {

    abstract val bindingInflater: (LayoutInflater) -> VB

    protected open val mBinding by lazy {
        bindingInflater.invoke(LayoutInflater.from(context))
    }


    protected open val dialog by lazy {
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

    override fun showDialog() {
        dialog.apply {
            setCancelable(true)
            setCanceledOnTouchOutside(true)
            show()

            window?.setBackgroundDrawable(null)


            setOnCancelListener {
                dismiss()
            }
        }

        initView()
        initListener()
    }

    override fun dismissDialog() {
        dialog.dismiss()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dialog.dismiss()
        owner.lifecycle.removeObserver(this)
    }
}