package com.onBit.pixelDemo.ui.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import com.blankj.utilcode.util.BarUtils
import com.onBit.PixelBitToolKit.databinding.ActivityRecyclewBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.pixelDemo.hit.module.Human
import com.onBit.pixelDemo.hit.module.ManType
import com.onBit.pixelDemo.hit.module.WomanType
import com.onBit.pixelDemo.viewmodel.MViewModel
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


    fun doSomeThing() {


    }

    override fun initView() {
        super.initView()
        viewModel.say()
        woman2.sex()
        man.sex()
        woman.sex()
//        lifecycleScope.launch(Dispatchers.IO) {
//            val bitmap = Glide.with(this@RecyclewActivity)
//                .asBitmap()
//                .load(R.mipmap.sun_100px)
//                .submit()
//                .get()
//            withContext(Dispatchers.Main){
//                bitmap?.let {
//                    mBinding.edgeView.setBitmap(it)
//                }
//            }
//        }

        startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS).also {
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            it.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        }.also { intent->
            (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.let { manager->
                manager.inputMethodList.firstOrNull { it.packageName == packageName }
            }?.let {
                it.id
            }?.also {
                intent.putExtra(":settings:fragment_args_key", it)
                intent.putExtra(":settings:show_fragment_args", Bundle().apply { putString(":settings:fragment_args_key", it) })
            }
        })
        mBinding.apply {
            callMeasure.setOnClickListener {
                (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.apply {
                    showInputMethodPicker()
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

