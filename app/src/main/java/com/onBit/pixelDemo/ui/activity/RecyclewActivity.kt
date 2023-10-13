package com.onBit.pixelDemo.ui.activity

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.green
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.blankj.utilcode.util.LogUtils
import com.example.lib_view.view.LedView
import com.onBit.PixelBitToolKit.databinding.ActivityRecyclewBinding
import com.onBit.lib_base.base.BaseActivity
import com.example.lib_view.R
import com.google.android.material.tabs.TabLayoutMediator
import com.onBit.pixelDemo.ui.fragment.MyFragment

class RecyclewActivity : BaseActivity<ActivityRecyclewBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityRecyclewBinding
        get() = ActivityRecyclewBinding::inflate

    override fun isFullScreen(): Boolean {
        return true
    }

    override fun isHideNavigation(): Boolean {
        return true
    }
    private val adapter by lazy {
        object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 10
            }

            override fun createFragment(position: Int): Fragment {
                return MyFragment(position)
            }

        }
    }

    override fun initView() {
        super.initView()

        mBinding.edgeView.apply {
            changeBorder(0,0)
            changeSize(50)
            val intArray = IntArray(7)
            intArray[0]=Color.YELLOW
            intArray[1]=Color.RED
            intArray[2]=Color.GREEN
            intArray[3]=Color.BLUE
            intArray[4]=Color.RED
            intArray[5]=Color.GREEN
            intArray[6]=Color.BLUE
            changeColor(intArray)
            changeSpeed(20)
        }


    }


    fun drawableToBitmap(drawableId: Int): Bitmap? {
        val drawable = ContextCompat.getDrawable(this, drawableId)
        if (drawable != null) {
            val bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            return bitmap
        }
        return null
    }


}