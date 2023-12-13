package com.onBit.pixelDemo.ui.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.blankj.utilcode.util.AppUtils.AppInfo
import com.bumptech.glide.Glide
import com.chad.library.adapter4.BaseMultiItemAdapter
import com.chad.library.adapter4.BaseQuickAdapter
import com.chad.library.adapter4.dragswipe.listener.DragAndSwipeDataCallback
import com.chad.library.adapter4.viewholder.QuickViewHolder
import com.onBit.PixelBitToolKit.R
import com.onBit.PixelBitToolKit.databinding.AdapterAppInfoBinding

class AppAdapter : BaseMultiItemAdapter<AppInfo>(), DragAndSwipeDataCallback {

//    override fun onBindViewHolder(holder: QuickViewHolder, position: Int, item: AppInfo?) {
//        AdapterAppInfoBinding.bind(holder.itemView).apply {
//            appTv.text = item?.name
//            Glide.with(context)
//                .load(item?.icon)
//                .into(appImg)
//        }
//    }

    init {
        addItemType(TITLE,object :OnMultiItemAdapterListener<AppInfo,QuickViewHolder>{
            override fun onBind(holder: QuickViewHolder, position: Int, item: AppInfo?) {

            }

            override fun onCreate(
                context: Context,
                parent: ViewGroup,
                viewType: Int
            ): QuickViewHolder {
                return QuickViewHolder(R.layout.layout_title, parent)
            }

        })
        addItemType(DATA,object :OnMultiItemAdapterListener<AppInfo,QuickViewHolder>{
            override fun onBind(holder: QuickViewHolder, position: Int, item: AppInfo?) {
                AdapterAppInfoBinding.bind(holder.itemView).apply {
                    appTv.text = item?.name
                    Glide.with(context)
                        .load(item?.icon)
                        .into(appImg)
                }
            }

            override fun onCreate(
                context: Context,
                parent: ViewGroup,
                viewType: Int
            ): QuickViewHolder {
                return  QuickViewHolder(R.layout.adapter_app_info, parent)
            }

        })
        onItemViewType(object :OnItemViewTypeListener<AppInfo>{
            override fun onItemViewType(position: Int, list: List<AppInfo>): Int {
                return if (position % 5 == 0) {
                    TITLE
                } else {
                    DATA
                }
            }
        })
    }

//    override fun onBindViewHolder(holder: ViewHolder, position: Int, item: AppInfo?) {
//        super.onBindViewHolder(holder, position, item)
//        when (getItemViewType(position)) {
//            TITLE -> {
//
//            }
//
//            else -> {
//                AdapterAppInfoBinding.bind(holder.itemView).apply {
//                    appTv.text = item?.name
//                    Glide.with(context)
//                        .load(item?.icon)
//                        .into(appImg)
//                }
//            }
//        }
//    }

//    override fun onCreateViewHolder(
//        context: Context,
//        parent: ViewGroup,
//        viewType: Int
//    ): QuickViewHolder {
//        return when (viewType) {
//            TITLE -> {
//                QuickViewHolder(R.layout.layout_title, parent)
//            }
//            else -> {
//                QuickViewHolder(R.layout.adapter_app_info, parent)
//            }
//        }
//    }

//    override fun getItemViewType(position: Int, list: List<AppInfo>): Int {
//        return if (position % 5 == 0) {
//            TITLE
//        } else {
//            DATA
//        }
//    }

    override fun dataMove(fromPosition: Int, toPosition: Int) {
        move(fromPosition, toPosition)
    }

    override fun dataRemoveAt(position: Int) {
        removeAt(position)
    }


    companion object {
        const val DATA = 0
        const val TITLE = 1
    }

}