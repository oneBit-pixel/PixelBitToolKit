package com.onBit.pixelDemo.ui.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.BaseEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.renderer.BubbleChartRenderer
import com.github.mikephil.charting.utils.ColorTemplate
import com.onBit.PixelBitToolKit.R
import com.onBit.PixelBitToolKit.databinding.ActivityChartBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.pixelDemo.ui.view.RoundedBarRenderer

class ChartActivity : BaseActivity<ActivityChartBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityChartBinding
        get() = ActivityChartBinding::inflate


    private val value by lazy {
        listOf(
            BarEntry(0f, floatArrayOf(0f,75f, 120f)),
            BarEntry(1f, floatArrayOf(0f,85f,100f)),
            BarEntry(2f, floatArrayOf(0f,25f,100f)),
            BarEntry(3f, floatArrayOf(0f,65f,100f)),
            BarEntry(4f, floatArrayOf(0f,55f,100f)),
            BarEntry(5f, floatArrayOf(0f,45f,100f)),
            BarEntry(6f, floatArrayOf(0f,35f,100f)),
            BarEntry(7f, floatArrayOf(0f,45f,100f)),
            BarEntry(8f, floatArrayOf(0f,55f,100f)),
            BarEntry(9f, floatArrayOf(0f,85f,100f)),
        )
    }

    override fun initView() {
        super.initView()
        mBinding.apply {
            val barDataSet = BarDataSet(value, "这是一个标题")
            barDataSet.colors = listOf(Color.TRANSPARENT,Color.TRANSPARENT,Color.BLUE)
            barDataSet.valueTextColor=Color.RED
            val arrayListOf = arrayListOf<IBarDataSet>(barDataSet)
            val barData = BarData(arrayListOf)
            barData.setDrawValues(true)
            barData.setValueTextSize(10f)

            chart.setTouchEnabled(true)
            chart.data = barData
            chart.setDrawValueAboveBar(false)

            chart.renderer
        }
    }

}