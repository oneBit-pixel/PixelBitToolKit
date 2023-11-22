package com.example.lib_view.circleDiskView

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.blankj.utilcode.util.LogUtils
import com.example.lib_view.R
import kotlin.math.min

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

    var imgBitmap: Bitmap? = null
        set(value) {
            field = value
            invalidate()
        }

    var imageSrc = 0
        set(value) {
            field = value
            if (value != 0) {
                imgBitmap = BitmapFactory.decodeResource(resources, value)
            }
        }
    lateinit var bitmapRectF: RectF
    val mColors = mutableListOf<Int>()

    init {
        context.obtainStyledAttributes(attr, R.styleable.CircleDiskView).apply {
            numberOfCircles = getInt(R.styleable.CircleDiskView_cd_number, DEFAULT_NUMBER)
            getResourceId(R.styleable.CircleDiskView_cd_colors, 0).let {
                if (it != 0) {
                    val colors = resources.getStringArray(it)
                    val map = colors.map { colorString ->
                        Color.parseColor(colorString)
                    }
                    mColors.addAll(map)
                }
            }
            imageSrc = getResourceId(R.styleable.CircleDiskView_android_src, 0)
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
                strokeWidth = 2f
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

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = (min(centerX, centerY) - 20).toFloat() // 半径

        //这个旋转
        drawArc(centerX, radius, centerY, canvas)

    }


    private fun isTouchPointInsideBitmap(x: Float, y: Float): Boolean {
        return bitmapRectF.contains(x, y)
    }


    private fun drawArc(
        centerX: Float,
        radius: Float,
        centerY: Float,
        canvas: Canvas
    ) {
        val rectF = RectF(
            centerX - radius, // 左边界
            centerY - radius, // 上边界
            centerX + radius, // 右边界
            centerY + radius   // 下边界
        )

        val sweepAngle = 360f / numberOfCircles // 扫描角度
        val startAngle = -sweepAngle - 90f // 起始角度

        canvas.save()
        paint.apply {
            style = Paint.Style.FILL_AND_STROKE
        }
        for (i in 0..numberOfCircles) {
            paint.color = mColors[i % mColors.size]
            path.reset()
            path.arcTo(rectF, startAngle, sweepAngle, true)
            path.lineTo(centerX, centerY)
            path.close()
            canvas.drawPath(path, paint)
            paint.color = Color.WHITE
            canvas.drawTextOnPath(i.toString(),path,20f,20f,paint)
            canvas.rotate(sweepAngle * i, centerX, centerY)
        }
        canvas.restore()
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