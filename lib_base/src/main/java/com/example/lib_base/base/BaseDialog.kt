package com.example.lib_base.base

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.viewbinding.ViewBinding

abstract class BaseDialog<VB : ViewBinding>(
    context: Context, binding: (LayoutInflater) -> VB
) : IDialog {

    protected val mBinding by lazy {
        binding.invoke(LayoutInflater.from(context))
    }

    protected val dialog by lazy {
        val builder = AlertDialog.Builder(context)
        builder.setView(mBinding.root)

        builder.create()
    }

    override fun showDialog() {
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.show()

        dialog.window?.setBackgroundDrawable(null)
        initView()
        initListener()
    }

    protected open fun initListener() {

    }

    protected open fun initView() {

    }


    override fun dismissDialog() {
        dialog.dismiss()
    }
}

interface IDialog {
    fun showDialog()
    fun dismissDialog()
}