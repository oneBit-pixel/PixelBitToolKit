package com.example.lib_view.streamView


import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.animation.addListener
import com.onBit.lib_base.base.extension.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class JunkFileLoadingProgress @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val scope = CoroutineScope(Dispatchers.Main)

    var firstProgress = 0L
    val trackPath by lazy { Path() }
    val trackPaint by lazy {
        Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = 10.dp()
            color = Color.WHITE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            this.isAntiAlias = true
        }
    }
    val bluePaint by lazy {
        Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = 10.dp()
            color = Color.WHITE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            this.isAntiAlias = true
        }
    }

    private var currentAngle = 0f
    private val animatorRed = ValueAnimator.ofFloat(0f, 1F)
    private val animatorBlue = ValueAnimator.ofFloat(0f, 1F)
    private val animatorYellow = ValueAnimator.ofFloat(0f, 1F)

    init {
        animatorRed.run {
            duration = 4000L
            repeatMode = ValueAnimator.REVERSE
            repeatCount = 0
            interpolator = LinearInterpolator()
            start()
        }
        animatorYellow.run {
            duration = 5000L
            repeatMode = ValueAnimator.REVERSE
            repeatCount = 0
            interpolator = LinearInterpolator()
            start()
        }
        animatorBlue.run {
            duration = 6000L
            repeatMode = ValueAnimator.REVERSE
            repeatCount = 0
            interpolator = LinearInterpolator()
            addUpdateListener {
                invalidate()
            }
           addListener(
               onEnd = {
                   scope.launch {
                       delay(5000L)
                       animatorRed.start()
                       animatorBlue.start()
                       animatorYellow.start()
                   }
               }
           )
            start()
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measureWidth = resolveSize(300.dp().roundToInt(), widthMeasureSpec)
        setMeasuredDimension(measureWidth, measureWidth / 2 + 30)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val startProgress = -180F
        val endProgress = 180F
        val paddingValue = trackPaint.strokeWidth / 2
        val rectF = RectF(
            paddingValue,
            paddingValue,
            measuredWidth - paddingValue,
            measuredWidth - paddingValue
        )
        trackPath.reset()
        trackPath.addArc(rectF, -startProgress, endProgress)
        trackPaint.color = Color.WHITE
        canvas.drawPath(trackPath, trackPaint)


        trackPath.reset()
        trackPath.addArc(rectF, -startProgress, endProgress * animatorRed.animatedValue as Float)
        val redPaint = trackPaint.apply {
            color = Color.parseColor("#FD1B14")
        }
        canvas.drawPath(trackPath, redPaint)

        trackPath.reset()
        trackPath.addArc(rectF, -startProgress, endProgress * animatorYellow.animatedValue as Float)
        val yellowPaint = trackPaint.apply { color = Color.parseColor("#FFC300") }
        canvas.drawPath(trackPath, yellowPaint)

        trackPath.reset()
        trackPath.addArc(rectF, -startProgress, endProgress * animatorBlue.animatedValue as Float)
        bluePaint.apply {
            shader = LinearGradient(
                rectF.left,
                rectF.top,
                rectF.centerX(),
                rectF.top,
                Color.parseColor("#00E5FF"),
                Color.parseColor("#0075FF"),
                Shader.TileMode.CLAMP
            )
        }
        canvas.drawPath(trackPath, bluePaint)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        scope.cancel()
        animatorRed.cancel()
        animatorBlue.cancel()
        animatorYellow.cancel()
    }


}