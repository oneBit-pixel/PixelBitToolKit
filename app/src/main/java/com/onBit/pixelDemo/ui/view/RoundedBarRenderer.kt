package com.onBit.pixelDemo.ui.view

import android.graphics.Canvas
import android.graphics.RectF
import com.github.mikephil.charting.animation.ChartAnimator
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.renderer.BarChartRenderer
import com.github.mikephil.charting.utils.ViewPortHandler


class RoundedBarRenderer    // 设置圆角的半径
    (
    chart: BarDataProvider?,
    animator: ChartAnimator?,
    viewPortHandler: ViewPortHandler?,
    private val mRadius: Float
) :
    BarChartRenderer(chart, animator, viewPortHandler) {
    override fun drawDataSet(c: Canvas, dataSet: IBarDataSet, index: Int) {

    }
}
