package com.onBit.pixelDemo.ui.activity

import android.graphics.Color
import android.view.LayoutInflater
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.renderer.RoundRenderer
import com.onBit.PixelBitToolKit.databinding.ActivityChartBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.pixelDemo.utls.XorUtils
import java.util.Base64

class ChartActivity : BaseActivity<ActivityChartBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityChartBinding
        get() = ActivityChartBinding::inflate



    private val value by lazy {
        listOf(
            BarEntry(0f, floatArrayOf(75f, 120f)).apply {
            },
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
        val hashMap = HashMap<String, Any>()
        hashMap["header"]=XorUtils.toMap()
        hashMap["data"]=XorUtils.toData()
        val toJson = GsonUtils.toJson(hashMap)
        LogUtils.d("toJson==>${toJson}")
        val xor = XorUtils.xor("original", toJson.toByteArray())
        val decode = XorUtils.decode("original","Yjt9eG14OyNiO3p2d218d207Izvxtq79pLn8mJ7xt6f9pLn/gbb9orz9oaP8uI3+pI7+kJX8lLn8lIX8oZE7ZDU7cXx4fXxrOyNiO3hpaTsjO3p2dDd2d1twbTdJcGF8dVtwbU12dnVScG07NTttajsjOyguKSsrLi4gISEoLCg7ZGQ=")
        val encode = XorUtils.encode("original", toJson)
        LogUtils.d("decode==>${decode}")
        LogUtils.d("encode==>${encode}")
        val bytes = android.util.Base64.decode(encode, android.util.Base64.NO_WRAP)
        val string = String(bytes)
        mBinding.apply {
            val barDataSet = BarDataSet(value, "这是一个标题")
            barDataSet.colors = listOf(Color.TRANSPARENT,Color.BLUE,Color.TRANSPARENT,Color.RED)
            barDataSet.highLightColor=Color.TRANSPARENT
            barDataSet.valueTextColor=Color.RED
            barDataSet.setDrawValues(true)


            val arrayListOf = arrayListOf<IBarDataSet>(barDataSet)
            val barData = BarData(arrayListOf)
//            barData.barWidth=0.8f
//            barData.setValueTextSize(10f)
            chart.data = barData
            chart.description= Description().apply {
                text="123"
                isEnabled=true
            }
            chart.setDrawMarkers(true)
            //展示标记
//            chart.marker=MarkerView(this@ChartActivity, R.layout.custom_marker_view).apply {
//                chartView=chart
//            }
            chart.renderer= RoundRenderer(chart,chart.animator,chart.viewPortHandler)

            chart.setTouchEnabled(true)
            chart.setDrawValueAboveBar(true)
            chart.isDragXEnabled=true
            chart.xAxis.apply {
                position= XAxis.XAxisPosition.BOTTOM
                setDrawGridLines(false)
                setDrawLabels(false)
                axisMinimum=-5f
                axisMaximum=chart.data.entryCount+5f
            }
            chart.setVisibleXRangeMaximum(7f)
            val dataSet = chart.data.getDataSetByIndex(0)
            LogUtils.d("数量==>${dataSet.entryCount}")
            chart.moveViewToX((dataSet.entryCount.toFloat())/2-5)
            chart.axisRight.apply {
                isEnabled=false
                setDrawLabels(false)
            }
            chart.axisLeft.apply {
                isEnabled=true
                setDrawAxisLine(false)
                setDrawGridLines(true)
                enableGridDashedLine(20f,10f,0f)
                axisMaximum=360f
                axisMinimum=35f
                setLabelCount(6,true)
            }
            chart.isScaleXEnabled=false
            chart.isScaleYEnabled=false
            chart.invalidate()

        }

    }

}