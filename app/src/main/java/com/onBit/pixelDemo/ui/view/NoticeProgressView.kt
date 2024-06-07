package com.onBit.pixelDemo.ui.view


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import com.onBit.lib_base.base.extension.dp
import com.onBit.lib_base.base.extension.sp
import kotlin.math.min
import kotlin.math.roundToInt

/**
 * 通知栏 进度条
 */
class NoticeProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val circularPaint by lazy {
        Paint().apply {
            this.style = Paint.Style.STROKE
            this.strokeWidth = 6.dp()
        }
    }

    private val textPaint by lazy {
        Paint().apply {
            this.style = Paint.Style.FILL_AND_STROKE
            this.strokeWidth = 1.dp()
            this.textSize = 10.sp()
            letterSpacing = 0.1F
        }
    }

    private var innerColor: Int = Color.RED

    private var progress = 20F
    private var bottomName = "battery"

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measureWidth = resolveSize(60.dp().roundToInt(), widthMeasureSpec)
        val measureHeight = resolveSize(60.dp().roundToInt(), heightMeasureSpec)
        setMeasuredDimension(measureWidth, measureHeight)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val measureText = textPaint.measureText(bottomName)
        val textHeight = textPaint.textSize

        val width = measuredWidth.toFloat()
        val height = measuredHeight.toFloat()

        val radius = min(width, height) - textHeight * 2

        drawStrokeCircular(canvas, radius)
    }


    private fun drawStrokeCircular(canvas: Canvas, radius: Float) {
        val path = Path()
        val offset = circularPaint.strokeWidth / 2
        val top = offset
        val left = (measuredWidth - radius) / 2 + offset
        val right = left + radius -offset
        val bottom = top + radius - offset
        val rectF = RectF(left, top, right, bottom)
        path.addArc(rectF, -90F, 360F)
        circularPaint.setColor(Color.parseColor("#D8D8D8"))
        canvas.drawPath(path, circularPaint)


        path.reset()
        path.addRect(rectF, Path.Direction.CW)
        val progressTv = "$progress%"
        val measureText = textPaint.measureText(progressTv)
        val offsetX = (rectF.right - rectF.left) / 2 - measureText / 2
        val offsetY = (rectF.bottom - rectF.top) / 2 + textPaint.textSize / 2
        canvas.drawTextOnPath(progressTv, path, offsetX, offsetY, textPaint)


        drawBottomText(canvas, rectF)
        drawInnerCircular(canvas,rectF)
    }

    private fun drawBottomText(canvas: Canvas, rectF: RectF) {
        val measureText = textPaint.measureText(bottomName)
        val offsetX = (rectF.right - rectF.left) / 2 - measureText / 2
        val offsetY = rectF.height() + textPaint.textSize + 7.dp()
        val path = Path()
        path.addRect(rectF, Path.Direction.CW)
        canvas.drawTextOnPath(bottomName, path, offsetX, offsetY, textPaint)
    }



    private fun drawInnerCircular(canvas: Canvas, rectF: RectF) {
        val path = Path()
        path.addArc(rectF, -90F, 360F * (progress / 100F))
        circularPaint.setColor(innerColor)
        canvas.drawPath(path, circularPaint)
    }

    fun setProgressColor(@ColorInt colorInt: Int) {
        innerColor = colorInt
        invalidate()
    }

    fun setProgress(progress: Int) {
        this.progress = progress.toFloat()
        invalidate()
    }

    fun setProgressName(name: String) {
        bottomName = name
        invalidate()
    }

}