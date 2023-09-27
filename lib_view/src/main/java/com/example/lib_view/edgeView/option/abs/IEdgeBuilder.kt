package com.example.lib_view.edgeView.option.abs

interface IEdgeBuilder {
    //设置动画种类
    fun setAnimType(): EdgeOption.Builder

    //设置颜色变化列表
    fun setColors(colors: List<Int>): EdgeOption.Builder
    fun setResourceDrawable(resourceDrawable: Int): EdgeOption.Builder

}