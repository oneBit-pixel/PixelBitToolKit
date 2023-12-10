package com.onBit.pixelDemo.ui.view

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import com.blankj.utilcode.util.LogUtils
import com.github.mikephil.charting.animation.ChartAnimator
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider
import com.github.mikephil.charting.renderer.BarLineScatterCandleBubbleRenderer
import com.github.mikephil.charting.utils.Transformer
import com.github.mikephil.charting.utils.ViewPortHandler

open class RoundRenderer : BarLineScatterCandleBubbleRenderer {

    protected val mChart: BarDataProvider
    protected val mBarRect = RectF()

    constructor(
        chart: BarDataProvider,
        animator: ChartAnimator,
        viewPortHandler: ViewPortHandler
    ) : super(
        animator,
        viewPortHandler
    ) {
        this.mChart = chart

        mHighlightPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mHighlightPaint.style = Paint.Style.FILL


    }


    override fun initBuffers() {

    }

    override fun drawData(c: Canvas?) {
        val barData = mChart.barData

        for (i in 0..barData.dataSetCount) {
            val set = barData.getDataSetByIndex(i)

            if (set.isVisible) {
                drawDataSet()
            }
        }
    }

    private fun drawDataSet() {

    }

    override fun drawValues(c: Canvas?) {

    }

    override fun drawValue(
        c: Canvas?,
        formatter: IValueFormatter?,
        value: Float,
        entry: Entry?,
        dataSetIndex: Int,
        x: Float,
        y: Float,
        color: Int
    ) {

    }

    override fun drawExtras(c: Canvas?) {

    }

    /**
     * Sets the drawing position of the highlight object based on the riven bar-rect.
     * @param high
     */
    protected open fun setHighlightDrawPos(high: Highlight, bar: RectF) {
        high.setDraw(bar.centerX(), bar.top)
    }


    override fun drawHighlighted(c: Canvas, indices: Array<Highlight>) {
        val barData = mChart.barData

        for (high in indices) {
            val set = barData.getDataSetByIndex(high.dataSetIndex)
            if (set == null || !set.isHighlightEnabled) continue
            val e = set.getEntryForXValue(high.x, high.y)
            if (!isInBoundsX(e, set)) continue
            val trans = mChart.getTransformer(set.axisDependency)
            mHighlightPaint.color = set.highLightColor
            mHighlightPaint.alpha = set.highLightAlpha
            val isStack = high.stackIndex >= 0 && e.isStacked
            val y1: Float
            val y2: Float
            if (isStack) {
                if (mChart.isHighlightFullBarEnabled) {
                    y1 = e.positiveSum
                    y2 = -e.negativeSum
                } else {
                    val realRange = e.realRanges[high.stackIndex]
                    y1 = realRange.from
                    y2 = realRange.to
                }
            } else {
                y1 = e.y
                y2 = 0f
            }
            LogUtils.d("y1==>${y1}")
            LogUtils.d("y2==>${y2}")
            LogUtils.d("isStack${isStack}")
            prepareBarHighlight(e.x, y1, y2, barData.barWidth / 2f, trans)
            setHighlightDrawPos(high, mBarRect)
            c.drawRect(mBarRect, mHighlightPaint)
        }
    }
    protected open fun prepareBarHighlight(
        x: Float,
        y1: Float,
        y2: Float,
        barWidthHalf: Float,
        trans: Transformer
    ) {
        val left = x - barWidthHalf
        val right = x + barWidthHalf
        mBarRect.set(left, y1, right, y2)
        trans.rectToPixelPhase(mBarRect, mAnimator.phaseY)
    }



}