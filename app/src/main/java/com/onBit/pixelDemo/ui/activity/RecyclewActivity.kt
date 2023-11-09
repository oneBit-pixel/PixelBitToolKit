package com.onBit.pixelDemo.ui.activity

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.blankj.utilcode.util.LogUtils
import com.onBit.PixelBitToolKit.databinding.ActivityRecyclewBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.pixelDemo.hit.entry.MyEntryPoint
import com.onBit.pixelDemo.hit.module.Human
import com.onBit.pixelDemo.hit.module.ManType
import com.onBit.pixelDemo.hit.module.Woman
import com.onBit.pixelDemo.hit.module.WomanType
import com.onBit.pixelDemo.viewmodel.MViewModel
import dagger.hilt.EntryPoints
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import javax.inject.Inject


@AndroidEntryPoint
class RecyclewActivity : BaseActivity<ActivityRecyclewBinding>() {


    val viewModel: MViewModel by viewModels()

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    @ManType
    lateinit var man: Human

    @Inject
    @WomanType
    lateinit var woman: Human

    @Inject
    @WomanType
    lateinit var woman2: Human

    override val bindingInflater: (LayoutInflater) -> ActivityRecyclewBinding
        get() = ActivityRecyclewBinding::inflate

    override fun isFullScreen(): Boolean {
        return true
    }

    fun doSomeThing() {


    }

    override fun initView() {
        super.initView()
        viewModel.say()
        woman2.sex()
        man.sex()
        woman.sex()
    }
}

