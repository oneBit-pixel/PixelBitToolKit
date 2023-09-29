package com.example.lib_view.edgeView.option

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import android.graphics.drawable.Drawable
import android.graphics.drawable.RotateDrawable
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.LogUtils
import com.example.lib_view.edgeView.EdgeView
import com.example.lib_view.edgeView.option.abs.EdgeOption
import java.util.logging.Level

class ImageEdgeOption : EdgeOption() {


    /**
     * 思路：
     * 1.先计算要放几个图像
     * 2.在对应图像开始设置距离 从而实现圆角
     * 3. 顶部宽度：从中间开始计算 如果范围在其中的图片 改变摆放位置消耗 依次消耗 topMiddle
     * 4. 底部宽度：从中间开始计算 最大值为 顶部宽度 当
     **/

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun drawEdge(edgeView: EdgeView, canvas: Canvas) {
        LogUtils.d("绘制..")
        val context = edgeView.context
        val mDrawable = context.getDrawable(resourceDrawable)!!

        val rotateDrawable = RotateDrawable().apply {
            drawable = mDrawable
            fromDegrees = 0f
            toDegrees = 90f
            pivotX = 0.5f
            pivotY = 0.5f
            level = 10_000
            setBounds(0, 0, drawableWidth, drawableHeight)
            colorFilter = ColorFilter()
        }


        // 绘制图片
        rotateDrawable.let {
            it.apply {
                fromDegrees = 0f
                toDegrees = 360f
                level = 0
            }


            var nextWith = 0
            var nextHeight = spacing

            //图片位置
            var left = leftMargin
            var top = topMargin
            var right = drawableWidth
            var bottom = drawableHeight

            //绘制上边
            do {
                //边角旋转
                if (left == leftMargin) {
                    it.level = (10_000 * 0.9).toInt()
                } else if (right+50>=edgeView.width){
                    it.level = (10_000 * 0.1).toInt()
                }else {
                    it.level = 0
                }
                topMiddle
                it.setBounds(left, top, right, bottom)
                it.draw(canvas)
                left += spacing + drawableWidth
                right += drawableWidth+spacing
                LogUtils.d("nextWith > edgeView.width==>${edgeView.width}")
            } while (right < edgeView.width)

//            //绘制右边
//            do {
//                it.setBounds(
//                    nextWith - width - spacing,
//                    nextHeight + spacing,
//                    edgeView.width - spacing,
//                    nextHeight + height
//                )
//                it.draw(canvas)
//                nextHeight += spacing + height
//            } while (nextHeight < edgeView.height)
//
//            nextWith = spacing + width
//
//            //绘制底部
//            do {
//                it.setBounds(
//                    nextWith + spacing,
//                    edgeView.height - height,
//                    nextWith + width,
//                    edgeView.height - spacing
//                )
//                it.draw(canvas)
//                nextWith += width + spacing
//                LogUtils.d("nextWith > edgeView.width==>${edgeView.width}")
//            } while (nextWith < edgeView.width)
//
//
//            nextHeight = spacing + height
//            //绘制左边
//            do {
//                it.setBounds(spacing, nextHeight, spacing + width, nextHeight + height)
//                it.draw(canvas)
//                nextHeight += height + spacing
//            } while (nextHeight < edgeView.height)
        }


    }

}