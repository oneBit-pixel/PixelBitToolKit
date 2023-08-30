package com.onBit.pixelDemo.ui.activity

import android.Manifest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.blankj.utilcode.util.LogUtils
import com.codeboy.mediafacer.MediaFacer
import com.codeboy.mediafacer.PictureGet
import com.hjq.permissions.XXPermissions
import com.onBit.PixelBitToolKit.R
import com.onBit.PixelBitToolKit.databinding.ActivityMediaBinding
import com.onBit.lib_base.base.BaseActivity

class MediaActivity : BaseActivity<ActivityMediaBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityMediaBinding
        get() = ActivityMediaBinding::inflate


    private val rexpermiss by lazy {
        XXPermissions.with(this)
    }
    override fun initView() {
        super.initView()
        mBinding.apply {
            button3.setOnClickListener {

                if (!XXPermissions.isGranted(this@MediaActivity, Manifest.permission.READ_MEDIA_IMAGES)) {
                    XXPermissions.with(this@MediaActivity).permission(Manifest.permission.READ_MEDIA_IMAGES)
                        .request { permissions, allGranted ->
                            println("allGranted==>$allGranted")
                            if (allGranted) {
                                val allAlbums = MediaFacer.withPictureContex(this@MediaActivity)
                                    .getAllPictureContents(PictureGet.externalContentUri)

                                LogUtils.d("allAblums==>${allAlbums.toString()}")
                            }
                        }
                }else{
                    val allAlbums = MediaFacer.withPictureContex(this@MediaActivity)
                        .getAllPictureContents(PictureGet.externalContentUri)

                    LogUtils.d("allAblums==>${allAlbums.toString()}")
                }
            }
        }


    }

}