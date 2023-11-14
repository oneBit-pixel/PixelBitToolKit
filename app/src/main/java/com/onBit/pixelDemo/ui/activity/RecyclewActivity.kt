package com.onBit.pixelDemo.ui.activity

import android.graphics.Color
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ScreenUtils
import com.bumptech.glide.Glide
import com.onBit.PixelBitToolKit.R
import com.onBit.PixelBitToolKit.databinding.ActivityRecyclewBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.lib_base.base.init.appContext
import com.onBit.pixelDemo.hit.entry.MyEntryPoint
import com.onBit.pixelDemo.hit.module.Human
import com.onBit.pixelDemo.hit.module.ManType
import com.onBit.pixelDemo.hit.module.Woman
import com.onBit.pixelDemo.hit.module.WomanType
import com.onBit.pixelDemo.viewmodel.MViewModel
import dagger.hilt.EntryPoints
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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


    fun doSomeThing() {


    }

    override fun initView() {
        super.initView()
        viewModel.say()
        woman2.sex()
        man.sex()
        woman.sex()
        lifecycleScope.launch(Dispatchers.IO) {
            val bitmap = Glide.with(this@RecyclewActivity)
                .asBitmap()
                .load(R.mipmap.sun_100px)
                .submit()
                .get()
            withContext(Dispatchers.Main){
                bitmap?.let {
                    mBinding.edgeView.setBitmap(it)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT)
        BarUtils.transparentNavBar(this)
    }
}

