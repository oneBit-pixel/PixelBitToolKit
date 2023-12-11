package com.example.lib_view.clockView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import java.util.Calendar
import kotlin.math.min

class ClockView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    //时钟内部圆形的半径
    private var mCircleWidth = 5f

    //时钟半径长度
    private var radius = 0f

    //整个圆的宽度
    private var mWidth = 0

    //整个时钟的高度
    private var mHeight = 0

    //长的刻度线
    private val scaleMax = 20f

    //短刻度线长度
    private val scaleMin = 10F
    private val textBounds by lazy {
        Rect()
    }

    //与刻度线的距离
    private val mNumberSpace = 20f

    //外层园画笔
    private val mPaint by lazy {
        Paint().apply {
            strokeWidth = mCircleWidth
            color = Color.BLACK
            style = Paint.Style.STROKE
        }
    }

    //时钟的宽度
    private var mHourWidth = 20F

    //秒针的宽度
    private var mSecondPointWidth = 20F

    private var mPointRange = 20F
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
                200
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //圆心X轴位置
        val centerX = (mWidth / 2).toFloat()
        //圆形Y轴位置
        val centerY = (mHeight / 2).toFloat()

        //绘制表盘
        drawClock(canvas, centerX, centerY)
//        绘制表盘刻度
        drawClockScale(canvas, centerX, centerY)
//        绘制数字
        drawClockNumber(canvas, centerX, centerY)
        //绘制指针
        drawPointer(canvas, centerX, centerY)
//        postInvalidateDelayed(1000)
    }

    /**
     * 绘制指针
     */
    private fun drawPointer(canvas: Canvas, centerX: Float, centerY: Float) {
        //获取当前时间
        val calendar = Calendar.getInstance()
        val hour = calendar[Calendar.HOUR]
        val minute = calendar[Calendar.MINUTE]
        val second = calendar[Calendar.SECOND]

        //计算时分秒转过的角度
        val angelHour = (hour + minute.toFloat() / 60) * 360 / 12
        val angelMinute = (minute + second.toFloat() / 60) * 360 / 12
        val angelSecond = second * 360 / 60

//        //绘制时针
//        canvas.save()
//        //旋转到时钟的角度
//        canvas.rotate(angelMinute, centerX, centerY)
//        val rectHour = RectF(
//
//        )


        // 绘制秒针
        canvas.save()
        // 旋转到分针的角度
        canvas.rotate(angelSecond.toFloat(), centerX, centerY)
        val rectSecond = RectF(
            centerX - mSecondPointWidth / 2,
            centerY - radius + 10,
            centerX + mSecondPointWidth / 2,
            centerY
        )
        // 设置秒针画笔属性
        mPaint.strokeWidth = mSecondPointWidth
        mPaint.color = Color.RED
        canvas.drawRoundRect(rectSecond, mPointRange, mPointRange, mPaint)
        canvas.restore()
    }

    private fun drawClockNumber(canvas: Canvas, centerX: Float, centerY: Float) {
        //开始绘制的坐标
        val x = centerX
        val y = scaleMax + mNumberSpace
        for (index in 1..60) {
            canvas.rotate(6F, centerX, centerY)
            if (index % 5 == 0) {
                mPaint.apply {
                    color = Color.BLACK
                    textSize = 35F
                    style = Paint.Style.FILL
                }
                mPaint.getTextBounds(
                    (index / 5).toString(),
                    0,
                    (index / 5).toString().length,
                    textBounds
                )
                val textWidth = textBounds.width()
                val textHeight = textBounds.height()
                canvas.save()
                canvas.rotate(
                    (-6F * index),
                    x - (textWidth / 2) + textBounds.centerX(),
                    y + textHeight + textBounds.centerY(),
                )
                canvas.drawText(
                    (index / 5).toString(),
                    x - (textWidth / 2),
                    y + textHeight,
                    mPaint
                )
                canvas.restore()
            } else {
                //小刻度
            }
        }
    }


    /**
     * 绘制表盘刻度 和 数字文本
     */
    /**
     * 绘制表盘刻度
     */
    private fun drawClockScale(canvas: Canvas, centerX: Float, centerY: Float) {
        for (index in 1..60) {
            // 刻度绘制以12点钟为准，每次将表盘旋转6°，后续绘制都以12点钟为基准绘制
            canvas.rotate(6F, centerX, centerY)
            // 绘制长刻度线
            if (index % 5 == 0) {
                // 设置长刻度画笔
                mPaint.apply {
                    strokeWidth = 4.0F
                    style = Paint.Style.STROKE
                }
                // 绘制刻度线
                canvas.drawLine(
                    centerX,
                    centerY - radius,
                    centerX,
                    centerY - radius + scaleMax,
                    mPaint
                )
            }
            // 绘制短刻度线
            else {
                // 设置短刻度画笔宽度
                mPaint.apply {
                    strokeWidth = 2.0F
                    style = Paint.Style.STROKE
                }
                canvas.drawLine(
                    centerX,
                    centerY - radius,
                    centerX,
                    centerY - radius + scaleMin,
                    mPaint
                )
            }
        }
    }


    private fun drawClock(canvas: Canvas, centerX: Float, centerY: Float) {
        mPaint.apply {
            strokeWidth = mCircleWidth
            color = Color.BLACK
            style = Paint.Style.STROKE
        }
        //设置外层圆画笔宽度
        canvas.drawCircle(centerX, centerY, radius, mPaint)
    }

}