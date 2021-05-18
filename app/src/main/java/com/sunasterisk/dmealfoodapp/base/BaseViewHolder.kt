package com.sunasterisk.dmealfoodapp.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.sunasterisk.dmealfoodapp.data.model.MealCategory

abstract class BaseViewHolder<T>(binding: ViewBinding, onItemClick: (T) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    private var itemData: T? = null

    init {
        binding.root.setOnClickListener { itemData?.let { onItemClick(it) } }
    }

    open fun onBindData(itemData: T) {
        this.itemData = itemData
    }
}
