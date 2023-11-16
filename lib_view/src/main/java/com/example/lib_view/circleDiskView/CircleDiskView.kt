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
import kotlin.math.cos
import kotlin.math.sin

class CircleDiskView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attributeSet, defStyle) {
    //扇形个数
    val numberOfCircles = 6
    val path by lazy { Path() }
    val paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            this.apply {
                color = Color.RED
                style = Paint.Style.STROKE
                strokeWidth = 20f
                strokeCap=Paint.Cap.ROUND
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
        /*绘制圆环*/
        paint.color=Color.RED
        val centerX = width / 2
        val centerY = height / 2
        val radius = centerX - 20f
        path.addCircle(
            centerX.toFloat(),
            centerY.toFloat(),
            radius,
            Path.Direction.CW
        )
        canvas.drawPath(path, paint)
        /*绘制线条*/
        val angleStep = 360f / numberOfCircles
        canvas.save()
        val stopY = centerY - radius + (20f)
        path.reset()
        paint.color = Color.BLUE
        for (i in 0..numberOfCircles) {
            canvas.rotate(angleStep, centerX.toFloat(), centerY.toFloat())
            path.moveTo(centerX.toFloat(), centerY.toFloat())
            path.lineTo(centerX.toFloat(), stopY)
            canvas.drawPath(path, paint)
        }
        canvas.restore()
        startRotationAnimation(this)
    }


    fun startRotationAnimation(view: View) {
        val animator = ObjectAnimator.ofFloat(view, "rotation", 0f, 1080f)
        animator.duration = 3000 // 设置动画持续时间，例如 3000 毫秒
        animator.repeatCount=ObjectAnimator.INFINITE
        animator.interpolator=AccelerateDecelerateInterpolator()
        animator.start()
    }
}