package com.onBit.pixelDemo.ui.activity

import android.content.Intent
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.onBit.PixelBitToolKit.R
import com.onBit.PixelBitToolKit.databinding.ActivityRecyclewBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.pixelDemo.ui.adapter.TestAdapter
import com.skydoves.powerspinner.createPowerSpinnerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.withContext

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
            button3
            spinner.setOnClickListener {

                spinner.showOrDismiss(0,-spinner.getSpinnerHeight()-150)
            }
        }
    }


}