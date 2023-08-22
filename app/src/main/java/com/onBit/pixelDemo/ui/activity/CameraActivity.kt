package com.onBit.pixelDemo.ui.activity

import android.hardware.Camera
import android.view.LayoutInflater
import androidx.annotation.Size
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.blankj.utilcode.util.LogUtils
import com.onBit.PixelBitToolKit.databinding.ActivityCameraBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.lib_camerax.CameraxHelper
import com.onBit.pixelDemo.utls.ImageProcessing
import com.tbruyelle.rxpermissions3.RxPermissions
import java.io.ByteArrayOutputStream

class CameraActivity : BaseActivity<ActivityCameraBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityCameraBinding
        get() = ActivityCameraBinding::inflate

    override fun initView() {
        super.initView()
        RxPermissions(this).request(android.Manifest.permission.CAMERA)
    }

    private val mCamera by lazy {
        CameraxHelper.getCamera()
    }

    override fun initListener() {
        super.initListener()
        mBinding.apply {
            CameraxHelper.setAnalysis(object : ImageAnalysis.Analyzer {
                override fun analyze(image: ImageProxy) {
                    val buffer = image.planes[0].buffer
                    val bufferSize = buffer.remaining()
                    val bytes = ByteArray(bufferSize)

                    buffer.get(bytes, 0, bytes.size)

                    val ouput = ByteArrayOutputStream()
                    ouput.write(bytes)

                    val data = ouput.toByteArray()

                    val imagAvg =
                        ImageProcessing.decodeYUV420888toRedAvg(image)


                    LogUtils.d("imagAvg==>$imagAvg")
                    if (imagAvg  <50) {
                        //没有检测到人手..
                        LogUtils.d("检测到人手了...")
                    } else {
                        LogUtils.d("没有检测到人手$imagAvg")
                    }

                    image.close()
                }
            })
        }
    }

}