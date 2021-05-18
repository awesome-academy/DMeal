package com.sunasterisk.dmealfoodapp.ui.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sunasterisk.dmealfoodapp.base.BaseAdapter
import com.sunasterisk.dmealfoodapp.base.BaseViewHolder
import com.sunasterisk.dmealfoodapp.data.model.MealCategory
import com.sunasterisk.dmealfoodapp.databinding.ItemMealCategoryBinding
import com.sunasterisk.dmealfoodapp.utils.loadImage

class MealCategoryAdapter(private val onItemClick: (MealCategory) -> Unit) :
    BaseAdapter<MealCategory, MealCategoryAdapter.MealCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealCategoryViewHolder =
        MealCategoryViewHolder(
            ItemMealCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onItemClick
        )

    class MealCategoryViewHolder(
        private val binding: ItemMealCategoryBinding,
        onItemClick: (MealCategory) -> Unit
    ) : BaseViewHolder<MealCategory>(binding, onItemClick) {

        override fun onBindData(itemData: MealCategory) {
            super.onBindData(itemData)
            with(binding) {
                itemData.apply {
                    textNameMealCategory.text = name
                    itemData.image?.let {
                        imageMealCategory.loadImage(it)
                    }
                }
            }
        }
    }
}
