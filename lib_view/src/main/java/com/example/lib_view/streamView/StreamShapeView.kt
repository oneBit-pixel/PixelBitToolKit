package com.example.lib_view.streamView

import android.animation.ValueAnimator
import android.annotation.SuppressLint
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
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import com.example.lib_view.R
import com.hjq.shape.layout.ShapeFrameLayout

@SuppressLint("Recycle")
class StreamShapeView @JvmOverloads constructor(
    context: Context,
    val attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ShapeFrameLayout(context, attrs, defStyle) {

    private val mPath = Path()
    private var mValueAnimator: ValueAnimator? = null
    private val mPaint = Paint().apply {
        color = Color.BLUE
    }

    var gradientColorArrays: IntArray = intArrayOf()

    init {

        context.obtainStyledAttributes(attrs, R.styleable.StreamShapeView)
            .apply {
                getResourceId(R.styleable.StreamShapeView_stream_colors, 0).let { colorResId ->
                    if (colorResId != 0) {
                        val colorStrings = resources.getStringArray(colorResId)
                        gradientColorArrays = colorStrings.map {
                            Color.parseColor(it)
                        }.toIntArray()
                    }
                }
            }.recycle()
    }

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
            addRoundRect(
                rectF,
                floatArrayOf(
                    shapeDrawableBuilder.topLeftRadius,
                    shapeDrawableBuilder.topLeftRadius,
                    shapeDrawableBuilder.topRightRadius,
                    shapeDrawableBuilder.topRightRadius,
                    shapeDrawableBuilder.bottomLeftRadius,
                    shapeDrawableBuilder.bottomLeftRadius,
                    shapeDrawableBuilder.bottomRightRadius,
                    shapeDrawableBuilder.bottomRightRadius,
                ),
                Path.Direction.CW
            )
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
                val colors =
                    intArrayOf(shapeDrawableBuilder.solidColor) + gradientColorArrays + intArrayOf(
                        shapeDrawableBuilder.solidColor
                    )

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
        canvas.drawPath(mPath, mPaint)
    }

}