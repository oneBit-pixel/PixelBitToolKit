package com.example.lib_view.clockView

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import java.nio.file.attribute.AttributeView
import kotlin.math.min

class ClockView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    //时钟内部圆形的半径
    private var mCircleWidth = 0f

    //时钟半径长度
    private var radius = 0f

    //整个圆的宽度
    private var mWidth = 0

    //整个时钟的高度
    private var mHeight = 0


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        //根据表盘半径+表盘圆环宽度计算View的宽度和高度
        mWidth = onMeasureSpec(widthMeasureSpec) + (mCircleWidth * 2).toInt()
        mHeight = onMeasureSpec(heightMeasureSpec) + (mCircleWidth * 2).toInt()
        radius = (mWidth) / 2 - mCircleWidth
        //设置View的最终宽高
        setMeasuredDimension(mWidth, mHeight)
    }

    private fun onMeasureSpec(measureSpec: Int): Int {
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        return when (specMode) {
            MeasureSpec.EXACTLY -> {
                //固定值或者mach_parent
                specSize
            }

            MeasureSpec.AT_MOST -> {
                //wrap_content,计算半径以宽高最小值为准
                min((radius * 2).toInt(), specSize)
            }

            else -> {
                0
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //圆心X轴位置
        val centerX = (mWidth / 2).toFloat()
        //圆形Y轴位置
        val centerY = (mHeight / 2).toFloat()


        //绘制表盘
        drawClock()

    }

    private fun drawClock() {

    }

}