package com.onBit.pixelDemo.ui.view

import android.graphics.Canvas
import android.graphics.RectF
import com.github.mikephil.charting.animation.ChartAnimator
import com.github.mikephil.charting.buffer.BarBuffer
import com.github.mikephil.charting.buffer.RoundBarBuffer
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.renderer.BarChartRenderer
import com.github.mikephil.charting.utils.Fill
import com.github.mikephil.charting.utils.Utils
import com.github.mikephil.charting.utils.ViewPortHandler

class RoundRenderer2 : BarChartRenderer {

    protected lateinit var roundBuffers: Array<RoundBarBuffer>

    constructor(
        chart: BarDataProvider,
        animator: ChartAnimator,
        viewPortHandler: ViewPortHandler
    ) : super(
        chart,
        animator,
        viewPortHandler
    ){

    }

    override fun initBuffers() {
        super.initBuffers()

        val barData = mChart.barData
        roundBuffers = arrayOf()

        for (i in mBarBuffers.indices) {
            val set: IBarDataSet = barData.getDataSetByIndex(i)
            roundBuffers[i] = RoundBarBuffer(
                set.entryCount * 4 * if (set.isStacked) set.stackSize else 1,
                barData.dataSetCount, set.isStacked
            )
        }

    }

    private val mBarShadowRectBuffer = RectF()

    override fun drawDataSet(c: Canvas?, dataSet: IBarDataSet, index: Int) {
        val trans = mChart.getTransformer(dataSet.axisDependency)

        mBarBorderPaint.color = dataSet.barBorderColor
        mBarBorderPaint.strokeWidth = Utils.convertDpToPixel(dataSet.barBorderWidth)

        val drawBorder = dataSet.barBorderWidth > 0f

        val phaseX = mAnimator.phaseX
        val phaseY = mAnimator.phaseY

        // draw the bar shadow before the values

        // draw the bar shadow before the values
//        if (mChart.isDrawBarShadowEnabled) {
//            mShadowPaint.color = dataSet.barShadowColor
//            val barData = mChart.barData
//            val barWidth = barData.barWidth
//            val barWidthHalf = barWidth / 2.0f
//            var x: Float
//            var i = 0
//            val count = Math.min(
//                Math.ceil((dataSet.entryCount.toFloat() * phaseX).toDouble()).toInt(),
//                dataSet.entryCount
//            )
//            while (i < count) {
//                val e = dataSet.getEntryForIndex(i)
//                x = e.x
//                mBarShadowRectBuffer.left = x - barWidthHalf
//                mBarShadowRectBuffer.right = x + barWidthHalf
//                trans.rectValueToPixel(mBarShadowRectBuffer)
//                if (!mViewPortHandler.isInBoundsLeft(mBarShadowRectBuffer.right)) {
//                    i++
//                    continue
//                }
//                if (!mViewPortHandler.isInBoundsRight(mBarShadowRectBuffer.left)) break
//                mBarShadowRectBuffer.top = mViewPortHandler.contentTop()
//                mBarShadowRectBuffer.bottom = mViewPortHandler.contentBottom()
//                c!!.drawRect(mBarShadowRectBuffer, mShadowPaint)
//                i++
//            }
//        }

        // initialize the buffer

        // initialize the buffer
        val buffer = roundBuffers[index]
        buffer.setPhases(phaseX, phaseY)
        buffer.setDataSet(index)
        buffer.setInverted(mChart.isInverted(dataSet.axisDependency))
        buffer.setBarWidth(mChart.barData.barWidth)

        buffer.feed(dataSet)

        trans.pointValuesToPixel(buffer.buffer)

        val isCustomFill = dataSet.fills != null && !dataSet.fills.isEmpty()
        val isSingleColor = dataSet.colors.size == 1
        val isInverted = mChart.isInverted(dataSet.axisDependency)


        if (isSingleColor) {
            mRenderPaint.color = dataSet.color
        }

        for (i in 0..buffer.size()) {
            var j = 0
            var pos = 0
            while (j < buffer.size()) {
                if (!mViewPortHandler.isInBoundsLeft(buffer.buffer[j + 2])) {
                    j += 4
                    pos++
                    continue
                }
                if (!mViewPortHandler.isInBoundsRight(buffer.buffer[j])) break
                if (!isSingleColor) {
                    // Set the color for the currently drawn value. If the index
                    // is out of bounds, reuse colors.
                    mRenderPaint.color = dataSet.getColor(pos)
                }
                if (isCustomFill) {
                    dataSet.getFill(pos)
                        .fillRect(
                            c, mRenderPaint,
                            buffer.buffer[j],
                            buffer.buffer[j + 1],
                            buffer.buffer[j + 2],
                            buffer.buffer[j + 3],
                            if (isInverted) Fill.Direction.DOWN else Fill.Direction.UP
                        )
                } else {
                    c!!.drawRect(
                        buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2],
                        buffer.buffer[j + 3], mRenderPaint
                    )
                }
                if (drawBorder) {
                    c!!.drawRect(
                        buffer.buffer[j], buffer.buffer[j + 1], buffer.buffer[j + 2],
                        buffer.buffer[j + 3], mBarBorderPaint
                    )
                }
                j += 4
                pos++
            }
        }
    }

    override fun drawHighlighted(c: Canvas, indices: Array<out Highlight>) {
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
            prepareBarHighlight(e.x, y1, y2, barData.barWidth / 2f, trans)
            setHighlightDrawPos(high, mBarRect)
            c.drawRect(mBarRect, mHighlightPaint)
        }
    }

}