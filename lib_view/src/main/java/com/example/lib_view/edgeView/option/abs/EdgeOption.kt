package com.example.lib_view.edgeView.option.abs

import android.graphics.Color
import com.example.lib_view.R

abstract class EdgeOption : IEdgeOption {


    protected val colors = mutableListOf(Color.RED)

    //间距
    protected var topMargin = 0f
    protected var leftMargin = 0f
    protected var rightMargin = 0f
    protected var bottomMargin = 0f

    //图标之间的距离
    protected var spacing = 0f

    //圆角
    protected var cornerTopLeft = 0f
    protected var cornerTopRight = 0f
    protected var cornerBottomLeft = 0f
    protected var cornerBottomRight = 0f

    //中间高度 (距离对应边的距离)
    protected var topMiddle = 0f
    protected var leftMiddle = 0f
    protected var rightMiddle = 0f
    protected var bottomMiddle = 0f


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
