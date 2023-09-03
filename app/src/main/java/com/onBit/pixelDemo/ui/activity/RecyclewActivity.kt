package com.onBit.pixelDemo.ui.activity

import android.view.LayoutInflater
import com.onBit.PixelBitToolKit.databinding.ActivityRecyclewBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.pixelDemo.ui.adapter.TestAdapter

class RecyclewActivity : BaseActivity<ActivityRecyclewBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityRecyclewBinding
        get() = ActivityRecyclewBinding::inflate

    private val list = mutableListOf(1, 2, 3, 4)


    private val adapter by lazy {
        TestAdapter().apply {
        }
    }

    override fun initView() {
        super.initView()
        mBinding.apply {
            recyclerview.adapter = adapter

            button2.setOnClickListener {
//                adapter.setList(list.reversed())
            }
        }

    }
}