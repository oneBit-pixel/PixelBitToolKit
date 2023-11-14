package com.example.lib_view.edgeView.animate

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.graphics.RectF
import android.graphics.SweepGradient


class EdgeAnimate : IEdgeBorderLight {

    private var mBitmap: Bitmap? = null
    private var width = 0
    private var isNorth = false
    private var sharp = ""
    private var infility = ""
    private var height = 0
    private val radii = floatArrayOf(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f)
    private val path by lazy { Path() }
    private var colors =
        intArrayOf(Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.RED, Color.DKGRAY)
    private val distance by lazy { 1.0F / (colors.size - 1) }
    private val positions by lazy { FloatArray(colors.size) }
    private var speed = 2.0f
    private var angle = 0.0f
    private val matrix by lazy { Matrix() }
    private val paint by lazy {
        Paint().apply {
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.MITER
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
            strokeWidth = 70f
        }
    }
    private val shader by lazy {
        SweepGradient(width / 2f, height / 2f, colors, positions)
    }

    init {
        initPositions()
    }

    private fun initPositions() {
        var i = 0
        while (true) {
            val iArr = colors
            if (i < iArr.size) {
                if (i == 0) {
                    positions[0] = this.distance / 2.0f
                } else if (i == iArr.size - 1) {
                    positions[iArr.size - 1] = 1.0f
                } else {
                    val fArr = positions
                    fArr[i] = fArr[i - 1] + this.distance
                }
                i++
            } else {
                return
            }
        }
    }

    override fun changeColor(iArr: IntArray) {

    }

    override fun onDraw(canvas: Canvas) {
        val f = this.angle + this.speed
        this.angle = f
        matrix.setRotate(f, (width / 2).toFloat(), (height / 2).toFloat())
        shader.setLocalMatrix(matrix)
        paint.shader = shader
        mBitmap?.let { bitmap ->
            drawBitmapByPath(bitmap, canvas)
        }
//        canvas.drawPath(path, paint)
    }

    private fun drawBitmapByPath(bitmap: Bitmap, canvas: Canvas) {
        val pathMeasure = PathMeasure(path, false)
        val pathLength = pathMeasure.length
        val bitmapWidth = bitmap.width.toFloat() // 设置Bitmap的宽度为20像素
        val bitmapHeight = bitmap.height.toFloat() // 设置Bitmap的高度为20像素

        val numBitmaps = (pathLength / bitmapWidth).toInt()
        val position = FloatArray(2) // 用于存储路径上的坐标
        val tan = FloatArray(2) // 用于存储路径上的切线

        for (i in 0..numBitmaps) {
            val distance = i * bitmapWidth/2
            pathMeasure.getPosTan(distance, position, tan)
            canvas.save()
            canvas.translate(position[0], position[1])
            val angle = (Math.atan2(
                tan[1].toDouble(),
                tan[0].toDouble()
            ) * 180.0 / Math.PI).toFloat()
            canvas.rotate(angle)

            // 绘制位图
            // 绘制位图，并指定目标矩形大小为20x20像素
            val destRect = RectF(0f, 0f, bitmapWidth / 4f, bitmapHeight / 4f)
            canvas.drawBitmap(bitmap, null, destRect, paint)

            // 恢复画布状态
            canvas.restore();
        }
    }

    override fun onLayout(width: Int, height: Int) {
        this.width = width
        this.height = height

        /*绘图*/
        path.reset()
        path.addRoundRect(
            RectF(0f, 0f, this.width.toFloat(), this.height.toFloat()),
            radii,
            Path.Direction.CW
        )
        path.close()
    }

    private fun drawPathLine(
        width: Int,
        height: Int,
        north: Boolean,
        sharp: String,
        infility: String
    ) {
        this.path.reset()
    }

    override fun setBitmap(bitmap: Bitmap) {
        this.mBitmap = bitmap
    }
}