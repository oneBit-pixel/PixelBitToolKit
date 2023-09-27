package com.example.lib_view.edgeView.factory

import com.example.lib_view.edgeView.option.ImageEdgeOption
import com.example.lib_view.edgeView.option.LineEdgeOption
import com.example.lib_view.edgeView.option.abs.EdgeOption
import com.example.lib_view.edgeView.option.abs.EdgeOption.EdgeType

object EdgeFactory {

    fun create(edgeType: EdgeType): EdgeOption.Builder {
        //创建
        return when (edgeType) {
            EdgeType.IMAGE -> {
                EdgeOption.Builder(
                    ImageEdgeOption()
                )
            }

            EdgeType.LINE -> {
                EdgeOption.Builder(
                    LineEdgeOption()
                )
            }
        }
    }


}