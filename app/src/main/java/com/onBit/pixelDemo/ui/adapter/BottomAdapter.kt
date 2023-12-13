package com.onBit.pixelDemo.ui.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.AppUtils.AppInfo
import com.chad.library.adapter4.BaseQuickAdapter
import com.chad.library.adapter4.BaseSingleItemAdapter
import com.chad.library.adapter4.loadState.LoadState
import com.chad.library.adapter4.loadState.trailing.TrailingLoadStateAdapter
import com.chad.library.adapter4.viewholder.QuickViewHolder
import com.onBit.PixelBitToolKit.R

class BottomAdapter : BaseQuickAdapter<AppInfo,QuickViewHolder>() {

    init {

    }
    override fun onBindViewHolder(holder: QuickViewHolder, position: Int, item: AppInfo?) {

    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): QuickViewHolder {
        return QuickViewHolder(R.layout.adapter_bottom,parent)
    }


}