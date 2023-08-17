package com.example.lib_camerax.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.view.ScaleGestureDetector
import com.blankj.utilcode.util.LogUtils
import com.example.lib_base.base.BaseFragment
import com.example.lib_camerax.CameraxHelper
import com.example.lib_camerax.databinding.FragmentCameraBinding
import com.tbruyelle.rxpermissions3.RxPermissions

class CameraFragment : BaseFragment<FragmentCameraBinding>(FragmentCameraBinding::inflate) {

    private val rxPermissions by lazy {
        RxPermissions(this)
    }

    override fun initView() {
        super.initView()

        initCamera()
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun initListener() {
        super.initListener()
        mBinding.apply {
            val gestureDetector = ScaleGestureDetector(requireContext(),
                object : ScaleGestureDetector.OnScaleGestureListener {
                    override fun onScale(detector: ScaleGestureDetector): Boolean {
                        CameraxHelper.setZoom(detector.scaleFactor)
                        return true
                    }

                    override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
                        return true
                    }

                    override fun onScaleEnd(detector: ScaleGestureDetector) {
                    }

                })

            previewView.setOnTouchListener { v, event ->
                gestureDetector.onTouchEvent(event)

                true
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun initCamera() {
        if (!rxPermissions.isGranted(Manifest.permission.CAMERA)) {
            rxPermissions.request(Manifest.permission.CAMERA)
                .subscribe { granted ->
                    if (granted) {
                        CameraxHelper.initCamera(
                            requireContext(),
                            mBinding.previewView
                        )
                    } else {
                        LogUtils.e("没有权限...")
                    }
                }
        } else {
            CameraxHelper.initCamera(
                requireContext(),
                mBinding.previewView
            )
        }
    }

}