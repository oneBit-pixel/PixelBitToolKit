package com.onBit.pixelDemo.ui.activity

import android.graphics.Color
import android.view.LayoutInflater
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.renderer.HorizontalBarChartRenderer
import com.onBit.PixelBitToolKit.databinding.ActivityChartBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.pixelDemo.ui.dialog.MyDialog
import com.onBit.pixelDemo.ui.view.RoundRenderer

class ChartActivity : BaseActivity<ActivityChartBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityChartBinding
        get() = ActivityChartBinding::inflate


    private val myDialog by lazy { MyDialog(this) }

    private val value by lazy {
        listOf(
            BarEntry(0f, floatArrayOf(75f, 120f)),
            BarEntry(1f, floatArrayOf(85f,100f)),
            BarEntry(2f, floatArrayOf(25f,100f)),
            BarEntry(3f, floatArrayOf(65f,100f)),
            BarEntry(4f, floatArrayOf(55f,100f)),
            BarEntry(5f, floatArrayOf(45f,100f)),
            BarEntry(6f, floatArrayOf(35f,100f)),
            BarEntry(7f, floatArrayOf(45f,100f)),
            BarEntry(8f, floatArrayOf(55f,100f)),
            BarEntry(9f, floatArrayOf(85f,100f)),
        )
    }

    override fun initView() {
        super.initView()
        mBinding.apply {
            val barDataSet = BarDataSet(value, "这是一个标题")
            barDataSet.colors = listOf(Color.TRANSPARENT,Color.BLUE)
            barDataSet.valueTextColor=Color.RED
            val arrayListOf = arrayListOf<IBarDataSet>(barDataSet)
            val barData = BarData(arrayListOf)
            barData.setDrawValues(true)
            barData.setValueTextSize(10f)

            chart.setTouchEnabled(true)
            chart.data = barData
            chart.renderer=RoundRenderer(chart,chart.animator,chart.viewPortHandler)
            chart.setDrawValueAboveBar(true)

            chart.setOnClickListener {
                myDialog.show()
            }
        }

    }

}