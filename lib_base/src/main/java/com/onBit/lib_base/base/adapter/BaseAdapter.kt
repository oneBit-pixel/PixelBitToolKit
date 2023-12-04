package com.onBit.lib_base.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.onBit.lib_base.base.adapter.dao.AdapterDao

abstract class BaseAdapter<T, VB : ViewBinding>(
) : RecyclerView.Adapter<BaseAdapter.ViewHolder<VB>>(), AdapterDao<T> {

    protected abstract val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB


    open var listener: OnItemClickListener? = null

    private val items = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<VB> {
        val binding = bindingInflater.invoke(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }



    override fun getItemCount(): Int = items.size


    class ViewHolder<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder<VB>, position: Int) {
        onBind(holder.binding, items[position], position)
    }

    override fun onBindViewHolder(
        holder: ViewHolder<VB>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)

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

    override fun setList(list: List<T>) {
        this.items.clear()
        this.items.addAll(list)
        notifyDataSetChanged()
    }
}