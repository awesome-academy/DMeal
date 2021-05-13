package com.sunasterisk.dmealfoodapp.ui.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sunasterisk.dmealfoodapp.R
import com.sunasterisk.dmealfoodapp.base.BaseAdapter
import com.sunasterisk.dmealfoodapp.base.BaseViewHolder
import com.sunasterisk.dmealfoodapp.data.model.MealCategory
import com.sunasterisk.dmealfoodapp.utils.loadImage
import kotlinx.android.synthetic.main.item_meal_category.view.*

class MealCategoryAdapter(private val onItemClick: (MealCategory) -> Unit) :
    BaseAdapter<MealCategory, MealCategoryAdapter.MealCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealCategoryViewHolder =
        MealCategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_meal_category, parent, false),
            onItemClick
        )

    class MealCategoryViewHolder(
        itemView: View,
        onItemClick: (MealCategory) -> Unit
    ) : BaseViewHolder<MealCategory>(itemView, onItemClick) {

        override fun onBindData(itemData: MealCategory) {
            super.onBindData(itemData)
            with(itemView) {
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
