package com.example.lib_view.edgeView.option.abs

import android.graphics.Canvas
import com.example.lib_view.edgeView.EdgeView

interface IEdgeOption  {
    fun drawEdge(edgeView: EdgeView, canvas: Canvas)

    //设置动画种类
    fun setAnimType()

    //设置颜色变化列表

}