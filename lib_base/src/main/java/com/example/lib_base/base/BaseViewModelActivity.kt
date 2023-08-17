package com.example.lib_base.base

import android.view.LayoutInflater
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseViewModelActivity<VB : ViewBinding,VM:ViewModel>(
    binding: (LayoutInflater) -> VB,
    viewModel:Class<VM>
) : BaseActivity<VB>() {

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