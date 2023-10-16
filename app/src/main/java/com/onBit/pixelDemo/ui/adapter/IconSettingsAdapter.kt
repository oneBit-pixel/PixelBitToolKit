package com.onBit.pixelDemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.onBit.PixelBitToolKit.databinding.AdapterIconSettingsBinding
import com.onBit.lib_base.base.adapter.BaseAdapter

class IconSettingsAdapter : BaseAdapter<String, AdapterIconSettingsBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> AdapterIconSettingsBinding
        get() = AdapterIconSettingsBinding::inflate

    private val mAdapter by lazy { IconAdapter().apply {
        setList(listOf(
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            ))
    } }

    override fun onBind(binding: AdapterIconSettingsBinding, item: String, position: Int) {
        binding.recyclerview.apply {
            layoutManager = GridLayoutManager(binding.root.context, 5)
            adapter = mAdapter
        }


    }
}