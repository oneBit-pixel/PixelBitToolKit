package com.example.lib_view.edgeView.animate

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.SweepGradient

class EdgeAnimate : IEdgeBorderLight {

    private var width = 0
    private var isNorth = false
    private var sharp = ""
    private var infility = ""
    private var height = 0
    private val radii = floatArrayOf(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f)
    private val path by lazy { Path() }
    private var colors = intArrayOf(Color.BLUE, Color.RED, Color.GREEN,Color.YELLOW,Color.RED,Color.DKGRAY)
    private val distance by lazy { 1.0F / (colors.size - 1) }
    private val positions by lazy { FloatArray(colors.size) }
    private var speed = 2.0f
    private var angle = 0.0f
    private val matrix by lazy { Matrix() }
    private val paint by lazy {
        Paint().apply {
            style = Paint.Style.FILL
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
        matrix.setRotate(f, (width/2).toFloat(), (height/2).toFloat())
        shader.setLocalMatrix(matrix)
        paint.shader = shader
        canvas.drawPath(path, paint)
    }

    override fun onLayout(width: Int, height: Int) {
        this.width = width/4
        this.height = height/4

        /*绘图*/
        path.reset()
        path.addRoundRect(
            RectF(200F, 200F, 200+this.width.toFloat(), this.height.toFloat()+200),
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

    }
}