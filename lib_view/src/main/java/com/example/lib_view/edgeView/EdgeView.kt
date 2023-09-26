package com.example.lib_view.edgeView

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.blankj.utilcode.util.LogUtils
import com.example.lib_view.R

/**
 * 边缘显示
 *
 * @return
 * @author zhangxuyang
 * @create 2023/9/26
 **/

class EdgeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    enum class EdgeType(val value: Int) {
        IMAGE(0),
        LINE(1);

        companion object {
            fun fromInt(value: Int): EdgeType {
                return values().firstOrNull { it.value == value } ?: IMAGE
            }
        }
    }

    private var edgeType: EdgeType = EdgeType.IMAGE

    init {
        context.obtainStyledAttributes(attrs, R.styleable.EdgeView).apply {
            try {
                edgeType = EdgeType.fromInt(getInt(R.styleable.EdgeView_edge_type, 0))

            } finally {
                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //开始绘制
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        LogUtils.d("onLayout")
    }


}
