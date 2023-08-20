package com.onBit.pixelDemo.ui.viewGroup

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.children
import com.blankj.utilcode.util.LogUtils
import com.onBit.PixelBitToolKit.R
import com.onBit.PixelBitToolKit.databinding.LayoutViewgroupBinding

@SuppressLint("ClickableViewAccessibility")
class TestFrameLayout constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(
    context,
    attrs,
    defStyleAttr
) {

    //默认值100dp
    private val dp100: Int

    private var layoutWidth: Int
    private var layoutHeight: Int

    private val binding: LayoutViewgroupBinding

    //最后的位置
    private var lastX: Float = 0f
    private var lastY: Float = 0f

    init {

        layoutParams= LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )

        dp100 = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            100.0f,
            context.resources.displayMetrics
        ).toInt()

        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.TestFrameLayout)
        layoutWidth =
            typedArray.getInt(R.styleable.TestFrameLayout_android_layout_width, dp100.toInt())

        layoutHeight =
            typedArray.getInt(R.styleable.TestFrameLayout_android_layout_height, dp100.toInt())
        typedArray.recycle()



        binding = LayoutViewgroupBinding.inflate(LayoutInflater.from(context), this, true)

//        setOnTouchListener { v, event ->
//            val edgeWidth = 20 // 边缘宽度，例如20像素
//            when (event.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    // 检查是否在边缘区域
//                    if (event.x < edgeWidth || event.x > v.width - edgeWidth ||
//                        event.y < edgeWidth || event.y > v.height - edgeWidth) {
//                        lastX = event.rawX
//                        lastY = event.rawY
//                    } else {
//                        return@setOnTouchListener false // 不在边缘区域，不处理触摸事件
//                    }
//                }
//
//                MotionEvent.ACTION_MOVE -> {
//
//                    val offsetX = event.rawX - lastX
//                    val offsetY = event.rawY - lastY
//
//                    animate().apply {
//                        duration = 0
//                        x(x + offsetX)
//                        y(y + offsetY)
//                    }
//
//                    lastX = event.rawX
//                    lastY = event.rawY
//                }
//
//                else -> {}
//            }
//
//            true
//        }


    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthModel = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        LogUtils.d("dp100==>${dp100.toInt()}")
        LogUtils.d("widthSize==>${widthSize}")
        setMeasuredDimension(dp100, dp100.toInt())
    }
}