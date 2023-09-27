package com.example.lib_view.edgeView.option.abs

import android.graphics.Color
import com.example.lib_view.R

abstract class EdgeOption : IEdgeOption {


    private val colors = mutableListOf(Color.RED)

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
