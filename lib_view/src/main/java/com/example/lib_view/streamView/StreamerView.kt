package com.example.lib_view.streamView

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import androidx.core.view.doOnPreDraw
import com.hjq.shape.layout.ShapeFrameLayout

/**
 * 光影控件
 */
class StreamerView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attr, defStyle) {
    private val mPaint = Paint().apply {
        color = Color.BLUE
    }
    private val mPath = Path()
    private var mValueAnimator: ValueAnimator? = null
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        initPointAndAnimator(widthSize.toFloat(), heightSize.toFloat())
    }

    private fun initPointAndAnimator(widthSize: Float, heightSize: Float) {
        val point1 = PointF(0f, 0f)
        val point3 = PointF(widthSize, heightSize)
        mPath.run {
            reset()
            val rectF = RectF(point1.x, point1.y, point3.x, point3.y)
            addRoundRect(rectF,200f,200f,Path.Direction.CW)
            close()
        }
        //斜率
        val k = heightSize / widthSize
        val offset = widthSize / 2
        mValueAnimator = ValueAnimator.ofFloat(-offset * 2, widthSize + offset * 2).apply {
            repeatCount = Animation.INFINITE
            interpolator = LinearInterpolator()
            duration = 1500
            addUpdateListener { animation ->
                val value = animation.animatedValue as Float
                val colors = intArrayOf(Color.parseColor("#444559"),Color.parseColor("#5E5F72"),Color.parseColor("#444559"))
                val gradient = LinearGradient(
                    value,
                    k * value,
                    value + offset,
                    k * (value + offset),
                    colors,
                    null,
                    Shader.TileMode.CLAMP
                )
                mPaint.shader = gradient
                invalidate()
            }
        }
        mValueAnimator?.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(mPath,mPaint)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mValueAnimator?.cancel()
        mValueAnimator = null
    }
}