package com.onBit.pixelDemo.ui.activity

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.LogUtils
import com.codeboy.mediafacer.MediaFacer
import com.codeboy.mediafacer.mediaGet.FileGet
import com.codeboy.mediafacer.mediaGet.PictureGet
import com.onBit.PixelBitToolKit.databinding.ActivityRecyclewBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.pixelDemo.hit.module.Human
import com.onBit.pixelDemo.hit.module.ManType
import com.onBit.pixelDemo.hit.module.WomanType
import com.onBit.pixelDemo.viewmodel.MViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import java.io.File
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

//        startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS).also {
//            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            it.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
//        }.also { intent ->
//            (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.let { manager ->
//                manager.inputMethodList.firstOrNull { it.packageName == packageName }
//            }?.let {
//                it.id
//            }?.also {
//                intent.putExtra(":settings:fragment_args_key", it)
//                intent.putExtra(
//                    ":settings:show_fragment_args",
//                    Bundle().apply { putString(":settings:fragment_args_key", it) })
//            }
//        })
//        mBinding.apply {
//            textView.setTypeface(Typeface.createFromAsset(assets,"AlexBrush-Regular.ttf"))
////            callMeasure.setOnClickListener {
////                (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.apply {
////                    showInputMethodPicker()
////                }
////            }
//        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }
        }



        mBinding.textView.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {

                val contents = MediaFacer.withDocContex(this@RecyclewActivity)
                    .getAllDocContent(*FileGet.DOCUMENT_MIME_TYPE)
                contents.filter {
                    it.fileName.endsWith("pdf", ignoreCase = true)
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

