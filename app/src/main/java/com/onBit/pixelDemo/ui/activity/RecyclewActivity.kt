package com.onBit.pixelDemo.ui.activity

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Shader
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.view.LayoutInflater
import androidx.camera.core.processing.SurfaceProcessorNode.In
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.blankj.utilcode.util.LogUtils
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.onBit.PixelBitToolKit.R
import com.onBit.PixelBitToolKit.databinding.ActivityRecyclewBinding
import com.onBit.PixelBitToolKit.databinding.LayoutTabItemBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.pixelDemo.ui.adapter.IconSettingsAdapter
import com.onBit.pixelDemo.ui.adapter.TestAdapter
import com.onBit.pixelDemo.ui.fragment.MyFragment


class RecyclewActivity : BaseActivity<ActivityRecyclewBinding>(), TabLayout.OnTabSelectedListener {
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

    private val iconType by lazy {
        listOf(
            getString(R.string.default1),
            getString(R.string.emoji),
            getString(R.string.animal),
            getString(R.string.cool),
            getString(R.string.nature),
        )
    }

    override fun initView() {
        super.initView()

        iconType.forEach { foo(1, 2) }

    }

    fun foo(x: Int) = { y: Int -> println("x+y==>${x + y}") } //foo(1).invoke(2)或foo(1)(2)

    val foo = { x: Int, y: Int -> println("x+y==>${x + y}") }//foo(1,2)或者foo.invoke(1,22)

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

    val a = curringLike("1234") { content ->
        println(content)
    }

    fun curringLike(content: String, block: (String) -> Unit) {
        block(content)
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        LogUtils.d("选中序号==>${tab?.position}")
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }


}