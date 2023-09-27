package com.example.lib_view.edgeView

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import com.blankj.utilcode.util.LogUtils
import com.example.lib_view.R
import com.example.lib_view.edgeView.factory.EdgeFactory
import com.example.lib_view.edgeView.option.abs.EdgeOption
import com.example.lib_view.edgeView.option.abs.EdgeOption.EdgeType

/**
 * 边缘照明空间
 *
 * @return
 * @author zhangxuyang
 * @create 2023/9/26
 **/

class EdgeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    var edgeType: EdgeType = EdgeType.IMAGE
        set(value) {
            field = value
            //
            invalidate()
        }
    var resourceDrawable: Int = R.drawable.mp_hourglass
        set(value) {
            field = value
            invalidate()
        }


    private var edgeOption: EdgeOption? = null

    init {
        //取消没有背景时 不绘画
        setWillNotDraw(false)
        context.obtainStyledAttributes(attrs, R.styleable.EdgeView).apply {
            try {
                edgeType = EdgeType.fromInt(getInt(R.styleable.EdgeView_edge_type, 0))
                resourceDrawable =
                    getResourceId(R.styleable.EdgeView_edge_src, R.drawable.mp_hourglass)
            } finally {
                recycle()
            }
        }
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        LogUtils.d("onDraw")
        //开始绘制
        edgeOption = EdgeFactory.create(edgeType)
            .setResourceDrawable(resourceDrawable)
            .build()
        edgeOption?.drawEdge(this, canvas)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        LogUtils.d("onLayout")
    }


}

