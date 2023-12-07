package com.onBit.lib_base.base

import android.view.LayoutInflater
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding


abstract class BaseViewModelActivity<VB : ViewBinding, VM : ViewModel>:
    BaseActivity<VB>() {

    abstract val viewModel: Class<VM>


    protected val mViewModel by lazy {
        ViewModelProvider(this)[viewModel]
    }


    override fun initListener() {
        super.initListener()
    }

    override fun initView() {
        super.initView()
    }

    override fun initEvent() {
        super.initEvent()
    }

}