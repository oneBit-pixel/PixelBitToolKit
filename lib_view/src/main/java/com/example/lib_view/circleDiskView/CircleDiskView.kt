package com.example.lib_view.circleDiskView

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import com.example.lib_view.R
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

@SuppressLint("Recycle")
class CircleDiskView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attr, defStyle) {

    companion object {
        const val DEFAULT_NUMBER = 6
    }

    var numberOfCircles: Int = 0
        set(value) {
            field = value
            invalidate()
        }

    init {
        context.obtainStyledAttributes(attr, R.styleable.CircleDiskView).apply {
            numberOfCircles = getInt(R.styleable.CircleDiskView_cd_number, DEFAULT_NUMBER)
            getResourceId(R.styleable.CircleDiskView_cd_colors, 0).let {
                if (it != 0) {
                    val colors = resources.obtainTypedArray(it)
                    colors.recycle()
                }
            }
            recycle()
        }
    }

    //扇形个数
    val path by lazy { Path() }
    val paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            this.apply {
                color = Color.RED
                style = Paint.Style.STROKE
                strokeWidth = 20f
                strokeCap = Paint.Cap.ROUND
            }
        }
    }
    val radiusOfEachCircle = 100f
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2
        val centerY = height / 2
        val radius = (min(centerX, centerY) - 20).toFloat() // 半径

        val rectF = RectF(
            centerX - radius, // 左边界
            centerY - radius, // 上边界
            centerX + radius, // 右边界
            centerY + radius  // 下边界
        )

        val startAngle = 0f // 起始角度
        val sweepAngle = 360f // 扫描角度，这里是完整的圆

        val useCenter = true // 是否使用圆心

        canvas.drawArc(rectF, startAngle, sweepAngle, useCenter, paint)
    }


    /*绘制线条*/
    private fun drawLines(
        canvas: Canvas,
        centerY: Int,
        radius: Float,
        centerX: Int
    ) {
        val angleStep = 360f / numberOfCircles
        canvas.save()
        val stopY = centerY - radius + (20f)
        paint.apply {
            color = Color.BLUE
            style = Paint.Style.STROKE
        }
        path.reset()
        for (i in 0..numberOfCircles) {
            canvas.rotate(angleStep, centerX.toFloat(), centerY.toFloat())
            path.moveTo(centerX.toFloat(), centerY.toFloat())
            path.lineTo(centerX.toFloat(), stopY)
            canvas.drawPath(path, paint)
        }
        canvas.restore()
    }
    /*绘制圆环*/

    private fun drawCircle(
        centerX: Int,
        centerY: Int,
        radius: Float,
        canvas: Canvas
    ) {
        paint.apply {
            color = Color.RED
            style = Paint.Style.STROKE
        }
        path.addCircle(
            centerX.toFloat(),
            centerY.toFloat(),
            radius,
            Path.Direction.CW
        )
        canvas.drawPath(path, paint)
    }


    fun startRotationAnimation(view: View) {
        val animator = ObjectAnimator.ofFloat(view, "rotation", 0f, 1080f)
        animator.duration = 3000 // 设置动画持续时间，例如 3000 毫秒
        animator.repeatCount = ObjectAnimator.INFINITE
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.start()
    }
}