package com.example.lib_base.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T, VB : ViewBinding>(
    var items: MutableList<T>,
    private val inflater: (LayoutInflater, ViewGroup?, Boolean) -> VB,
//    private val binder: (VB, T, Int) -> Unit
) : RecyclerView.Adapter<BaseAdapter.ViewHolder<VB>>() {

    open var listener: OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<VB> {
        val binding = inflater.invoke(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size


    class ViewHolder<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder<VB>, position: Int) {
        onBind(holder.binding, items[position], position)
    }

    abstract fun onBind(binding: VB, item: T, position: Int)


    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClicked(position: Int)
    }

    open fun onItemClicked(position: Int) {
        listener?.onItemClicked(position)
    }
}