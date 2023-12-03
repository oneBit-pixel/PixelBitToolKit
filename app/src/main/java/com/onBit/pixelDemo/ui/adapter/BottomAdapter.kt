package com.onBit.pixelDemo.ui.adapter

import android.content.Context
import android.view.ViewGroup
import com.chad.library.adapter4.BaseSingleItemAdapter
import com.chad.library.adapter4.viewholder.QuickViewHolder
import com.onBit.PixelBitToolKit.R

class BottomAdapter : BaseSingleItemAdapter<Any, QuickViewHolder>() {
    override fun onBindViewHolder(holder: QuickViewHolder, item: Any?) {

    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): QuickViewHolder {
        return QuickViewHolder(R.layout.adapter_bottom, parent)
    }
}