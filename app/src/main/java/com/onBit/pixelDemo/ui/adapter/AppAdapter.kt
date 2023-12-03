package com.onBit.pixelDemo.ui.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.blankj.utilcode.util.AppUtils.AppInfo
import com.bumptech.glide.Glide
import com.chad.library.adapter4.BaseQuickAdapter
import com.chad.library.adapter4.dragswipe.listener.DragAndSwipeDataCallback
import com.chad.library.adapter4.viewholder.QuickViewHolder
import com.onBit.PixelBitToolKit.R
import com.onBit.PixelBitToolKit.databinding.AdapterAppInfoBinding

class AppAdapter : BaseQuickAdapter<AppInfo, QuickViewHolder>(),DragAndSwipeDataCallback {

    override fun onBindViewHolder(holder: QuickViewHolder, position: Int, item: AppInfo?) {
        AdapterAppInfoBinding.bind(holder.itemView).apply {
            appTv.text = item?.name
            Glide.with(context)
                .load(item?.icon)
                .into(appImg)
        }
    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): QuickViewHolder {
        return QuickViewHolder(R.layout.adapter_app_info, parent)
    }

    override fun dataMove(fromPosition: Int, toPosition: Int) {
        move(fromPosition,toPosition)
    }

    override fun dataRemoveAt(position: Int) {
        removeAt(position)
    }


}