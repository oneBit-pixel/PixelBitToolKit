package com.onBit.pixelDemo.ui.activity

import android.graphics.Color
import android.view.LayoutInflater
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.renderer.BarChartRenderer
import com.github.mikephil.charting.renderer.HorizontalBarChartRenderer
import com.onBit.PixelBitToolKit.databinding.ActivityChartBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.pixelDemo.ui.view.RoundRenderer

class ChartActivity : BaseActivity<ActivityChartBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityChartBinding
        get() = ActivityChartBinding::inflate



    private val value by lazy {
        listOf(
            BarEntry(0f, floatArrayOf(75f, 120f)),
            BarEntry(1f, floatArrayOf(75f,130f)),
            BarEntry(2f, floatArrayOf(75f,140f)),
            BarEntry(3f, floatArrayOf(75f,150f)),
            BarEntry(4f, floatArrayOf(75f,160f)),
            BarEntry(5f, floatArrayOf(75f,170f)),
            BarEntry(6f, floatArrayOf(75f,190f)),
            BarEntry(7f, floatArrayOf(75f,120f)),
            BarEntry(8f, floatArrayOf(75f,190f)),
            BarEntry(9f, floatArrayOf(75f,300f)),
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
//            barData.barWidth=0.8f
//            barData.setValueTextSize(10f)
            chart.data = barData
            chart.renderer=RoundRenderer(chart,chart.animator,chart.viewPortHandler)

//            chart.setTouchEnabled(true)
//            chart.setDrawValueAboveBar(true)
//            chart.isDragXEnabled=true
//            chart.xAxis.apply {
//                position=XAxis.XAxisPosition.BOTTOM
//                setDrawGridLines(false)
//                setDrawLabels(false)
//                axisMinimum=-5f
//                axisMaximum=chart.data.entryCount+5f
//            }
//            chart.setVisibleXRangeMaximum(7f)
//            chart.moveViewToX(2f)
//            chart.axisRight.apply {
//                isEnabled=false
//                setDrawLabels(false)
//            }
//            chart.axisLeft.apply {
//                isEnabled=true
//                setDrawAxisLine(false)
//                setDrawGridLines(true)
//                enableGridDashedLine(20f,10f,0f)
//                axisMaximum=400f
//                axisMinimum=35f
//                setLabelCount(6,true)
//            }
//            chart.invalidate()

        }

    }

}