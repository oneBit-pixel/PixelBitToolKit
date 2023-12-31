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
        for (i in 0..numberOfCircles) {
            paint.apply {
                color = mColors[i % mColors.size]
                strokeWidth = 1f
                style = Paint.Style.FILL_AND_STROKE
            }
            path.reset()
            path.arcTo(rectF, startAngle, sweepAngle, true)
            path.lineTo(centerX, centerY)
            path.close()
            //绘制扇形
            canvas.drawPath(path, paint)
            paint.apply {
                color = Color.WHITE
                textSize = 20f
                style = Paint.Style.STROKE
            }
            val text = "这是第${i}扇形"
            val measureText = paint.measureText(text)
            //绘制文字
            canvas.drawTextOnPath(
                text, path,
                ((sweepAngle / 360f) * Math.PI * radius - (measureText / 2)).toFloat(),
                centerY - radius, paint
            )
            //绘制图片
            val bitmap = BitmapFactory.decodeResource(resources,R.mipmap.mp_1) // 替换为你的图片资源
            val bitmapWidth = bitmap.width
            val bitmapHeight = bitmap.height
            val x = (radius - bitmapWidth) / 2
            val y = radius/2 // 调整图片与文本之间的垂直间距
            val rectF1 = RectF(0f, 0f, 20f, 20f)
            canvas.drawBitmap(bitmap, null,rectF1, null)

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