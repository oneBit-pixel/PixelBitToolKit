package com.example.lib_camerax

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.provider.MediaStore
import android.view.OrientationEventListener
import android.view.ScaleGestureDetector
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.blankj.utilcode.util.LogUtils
import java.io.File

@SuppressLint("StaticFieldLeak")
object CameraxHelper {

    private var takePhotoListener: OnTakePhotoListener? = null
    private var previewView: PreviewView? = null

    private var mCamera: Camera? = null
    private var zoomRatio = 1f

    private val imageCapture by lazy {
        ImageCapture.Builder()
            .setTargetRotation(previewView!!.display.rotation)
            .build()
    }

    private var isFlash: Boolean = false

    //默认后置摄像头
    private var lensFacing: Int = CameraSelector.LENS_FACING_BACK

    /**
     * 保存到相册
     *
     * @param null
     * @return
     * @author zhangxuyang
     * @create 2023/8/12
     **/

    private val albumOption by lazy {
        ImageCapture.OutputFileOptions.Builder(
            context?.contentResolver!!,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, System.currentTimeMillis())
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            }
        ).build()
    }
    val cacheFile by lazy {
        File(context?.cacheDir, "${System.currentTimeMillis()}.jpg")
    }

    private val cacheOption by lazy {
        ImageCapture.OutputFileOptions.Builder(
            cacheFile
        ).build()
    }


    private var context: Context? = null


    /**
     *  初始化相机
     *
     * @return
     * @author zhangxuyang
     * @create 2023/8/12
     **/
    fun initCamera(context: Context, previewView: PreviewView) {
        this.context = context
        this.previewView = previewView
        startCamera()

        val orientationEventListener = object : OrientationEventListener(context) {
            override fun onOrientationChanged(orientation: Int) {
                val rotation = previewView.display.rotation
                imageCapture.targetRotation = rotation // 更新目标旋转
            }
        }
        orientationEventListener.enable()

    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context!!)
        val cameraProvider = cameraProviderFuture.get()
        //重新绑定
        cameraProviderFuture.addListener(
            {
                cameraProvider.unbindAll()
                bindPreview(cameraProvider)
            }, ContextCompat.getMainExecutor(context!!)
        )
    }

    /**
     * 与previewView 绑定
     *
     * @param null
     * @return
     * @author zhangxuyang
     * @create 2023/8/12
     **/

    private fun bindPreview(cameraProvider: ProcessCameraProvider) {
        var preview: Preview = Preview.Builder()
            .build()

        var cameraSelector: CameraSelector = CameraSelector.Builder()
            .requireLensFacing(lensFacing)
            .build()

        preview.setSurfaceProvider(previewView?.surfaceProvider)

        mCamera = cameraProvider.bindToLifecycle(
            context as LifecycleOwner,
            cameraSelector,
            preview,
            imageCapture
        )
    }


    //切换镜头
    fun toggleCamera() {
        lensFacing = if (lensFacing == CameraSelector.LENS_FACING_BACK) {
            CameraSelector.LENS_FACING_FRONT
        } else {
            CameraSelector.LENS_FACING_BACK
        }
        startCamera()
    }

    //打开摄像头
    fun toggleFlashLight() {
        mCamera?.let {
            isFlash = !isFlash
            it.cameraControl.enableTorch(isFlash)
        }
    }

    /**
     * 拍照
     *
     * @return
     * @author zhangxuyang
     * @create 2023/8/12
     **/

    fun takePhoto(isSave: Boolean = false) {
        if (!isSave) {
            imageCapture.takePicture(
                cacheOption,
                ContextCompat.getMainExecutor(context!!),
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        LogUtils.d("拍照成功...")
                        takePhotoListener?.success(cacheFile.absolutePath)
                    }

                    override fun onError(exception: ImageCaptureException) {
                        LogUtils.e("拍照失败...")
                    }

                }
            )
        } else {
            imageCapture.takePicture(albumOption,
                ContextCompat.getMainExecutor(context!!),
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        LogUtils.d("拍照成功...")

                    }

                    override fun onError(exception: ImageCaptureException) {
                        LogUtils.e("拍照失败...")
                    }

                }
            )
        }
    }

    /**
     * 获取正确方向的图片
     *
     * @return
     * @author zhangxuyang
     * @create 2023/8/12
     **/

    fun getCorrectlyOrientedBitmap(path: String): Bitmap? {
        // 从路径加载Bitmap
        val originalBitmap = BitmapFactory.decodeFile(path) ?: return null

        // 读取Exif方向标签
        val exif = ExifInterface(path)
        val orientation = exif.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL
        )

        // 计算旋转角度
        val rotationAngle = when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> 90f
            ExifInterface.ORIENTATION_ROTATE_180 -> 180f
            ExifInterface.ORIENTATION_ROTATE_270 -> 270f
            else -> 0f
        }

        // 如果需要，旋转Bitmap
        return if (rotationAngle != 0f) {
            val matrix = Matrix()
            matrix.postRotate(rotationAngle)
            Bitmap.createBitmap(
                originalBitmap,
                0,
                0,
                originalBitmap.width,
                originalBitmap.height,
                matrix,
                true
            )
        } else {
            originalBitmap
        }
    }


    interface OnTakePhotoListener {
        fun success(imagePath: String)

        fun failure()
    }

    fun setOnTakePhotoListener(takePhotoListener: OnTakePhotoListener) {
        this.takePhotoListener = takePhotoListener
    }

    /**
     * 设置摄像头的缩放
     *
     * @param scaleFactor 放大缩小系数
     * @return
     * @author zhangxuyang
     * @create 2023/8/12
     **/
    fun setZoom(scaleFactor: Float) {
        mCamera?.let {
            zoomRatio *= scaleFactor
            val zoomState = it.cameraInfo.zoomState.value!!
            zoomRatio = zoomRatio.coerceIn(zoomState.minZoomRatio, zoomState.maxZoomRatio)
            it.cameraControl.setZoomRatio(zoomRatio)
        }

    }
}

