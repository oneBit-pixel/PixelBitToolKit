package com.example.lib_base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
abstract class BaseFragment<T : ViewBinding>(
    binding:(LayoutInflater)->T
) : Fragment() {

    protected  open val mBinding by lazy {
        binding.invoke(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initEvent()
    }

    protected open fun initEvent() {

    }

    protected open fun initListener() {

    }

    protected open fun initView() {

    }


}