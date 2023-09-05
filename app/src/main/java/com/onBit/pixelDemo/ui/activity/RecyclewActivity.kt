package com.onBit.pixelDemo.ui.activity

import android.content.Intent
import android.view.LayoutInflater
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onBit.PixelBitToolKit.databinding.ActivityRecyclewBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.pixelDemo.ui.adapter.TestAdapter

class RecyclewActivity : BaseActivity<ActivityRecyclewBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityRecyclewBinding
        get() = ActivityRecyclewBinding::inflate

    private val list = mutableListOf(1, 2, 3, 4)


    private val adapter by lazy {
        TestAdapter()
    }



    override fun initView() {
        super.initView()
        mBinding.apply {
            button3.setOnClickListener {
                startActivity(
                    Intent(this@RecyclewActivity,MainActivity::class.java)
                )
            }
        }

    }
}