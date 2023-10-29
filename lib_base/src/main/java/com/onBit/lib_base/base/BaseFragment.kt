package com.onBit.lib_base.base

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ColorUtils
import com.onBit.lib_base.base.utils.ColorUtilsEx

abstract class BaseFragment<T : ViewBinding>(
    binding:(LayoutInflater)->T
) : Fragment() {

    protected  open val mBinding by lazy {
        binding.invoke(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTheme()
        initView()
        initListener()
        initEvent()
    }

    private fun setTheme() {

    }

    protected open fun initEvent() {

    }

    protected open fun initListener() {

    }

    protected open fun initView() {

    }

    protected open fun setStatusByBg(background: Drawable) {
        if (background is BitmapDrawable) {
            setBitmapColor(background.bitmap)
        } else if (background is ColorDrawable) {
            setStatusColor(background.color)
            setStatusLight(background.color)
        }
    }

    //从bitmap中提取主色 设置到状态栏上
    protected open fun setBitmapColor(bitmap: Bitmap) {
        ColorUtilsEx.getColorFromBitmap(bitmap) { color ->
            setStatusColor(color)
            setStatusLight(color)
        }
    }
    protected open fun setStatusLight(color: Int) {
        BarUtils.setStatusBarLightMode(requireActivity(), ColorUtils.isLightColor(color))
    }
    private fun setStatusColor(color: Int) {
        BarUtils.setStatusBarColor(requireActivity(), color)
    }


}