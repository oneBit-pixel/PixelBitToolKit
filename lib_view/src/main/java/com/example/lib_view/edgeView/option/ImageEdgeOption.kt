package com.example.lib_view.edgeView.option

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Color
import com.blankj.utilcode.util.LogUtils
import com.example.lib_view.edgeView.EdgeView
import com.example.lib_view.edgeView.option.abs.EdgeOption

class ImageEdgeOption : EdgeOption() {

    /**
     * 思路：1.先计算要放几个图像
     * 2.在对应图像开始设置距离 从而实现圆角
     **/

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun drawEdge(edgeView: EdgeView, canvas: Canvas) {
        LogUtils.d("绘制..")
        val context = edgeView.context
        val drawable = context.getDrawable(resourceDrawable)!!

        var totalSize = edgeView.width + edgeView.height

        val width = 50
        val height = 100

        // 设置图片间隔（根据需要调整）
        val spacing = 0

        // 计算绘制图片的位置
        val left = 0 // 左边距
        val top = 0  // 上边距


        // 绘制图片
        drawable?.let {

            var nextWith = spacing

            //绘制上边
            do {
                it.setBounds(nextWith + spacing, top + spacing, nextWith + width, top + height)
                it.draw(canvas)
                nextWith += width + spacing
                LogUtils.d("nextWith > edgeView.width==>${edgeView.width}")
            } while (nextWith < edgeView.width)

            var nextHeight = height
            //绘制右边
            do {
                it.setBounds(
                    nextWith - width - spacing,
                    nextHeight + spacing,
                    edgeView.width - spacing,
                    nextHeight + height
                )
                it.draw(canvas)
                nextHeight += spacing + height
            } while (nextHeight < edgeView.height)


        }
    }

}