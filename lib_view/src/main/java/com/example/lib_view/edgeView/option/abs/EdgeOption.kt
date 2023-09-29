package com.example.lib_view.edgeView.option.abs

import android.graphics.Color
import com.example.lib_view.R

abstract class EdgeOption : IEdgeOption {


    protected val colors = mutableListOf(Color.RED)

    //图片宽高
    protected var drawableWidth = 50
    protected var drawableHeight = 100

    //间距
    protected var topMargin = 0
    protected var leftMargin = 0
    protected var rightMargin = 0
    protected var bottomMargin = 0

    //图标之间的距离
    protected var spacing = 0

    //圆角
    protected var cornerTopLeft = 0
    protected var cornerTopRight = 0
    protected var cornerBottomLeft = 0
    protected var cornerBottomRight = 0

    //中间高度 (距离对应边的距离)
    protected var topMiddle = 0
    protected var leftMiddle = 0
    protected var rightMiddle = 0
    protected var bottomMiddle = 0

    //

    var resourceDrawable: Int = R.drawable.mp_hourglass
        set(value) {
            field = value
        }


    fun setColors(colors: List<Int>) {
        this.colors.clear()
        this.colors.addAll(colors)
    }

    override fun setAnimType() {

    }


    class Builder(private val option: EdgeOption) : IEdgeBuilder {


        override fun setAnimType(): Builder {
            return this
        }

        override fun setColors(colors: List<Int>): Builder {
            option.setColors(colors)
            return this
        }

        override fun setResourceDrawable(resourceDrawable: Int): Builder {
            option.resourceDrawable = resourceDrawable
            return this
        }


        fun build(): EdgeOption {
            return option
        }
    }

    enum class EdgeType(val value: Int) {
        IMAGE(0),
        LINE(1);

        companion object {
            fun fromInt(value: Int): EdgeType {
                return values().firstOrNull { it.value == value } ?: IMAGE
            }
        }
    }
}
