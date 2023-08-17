package com.example.lib_base.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected abstract val bindingInflater: (LayoutInflater) -> VB

    protected open val mBinding by lazy {
        bindingInflater.invoke(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        initView()
        initListener()
        initEvent()
    }

    protected open fun initListener() {

    }

    protected open fun initEvent() {

    }

    protected open fun initView() {

    }

}