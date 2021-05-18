package com.sunasterisk.dmealfoodapp.ui.listmeal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sunasterisk.dmealfoodapp.base.BaseAdapter
import com.sunasterisk.dmealfoodapp.base.BaseViewHolder
import com.sunasterisk.dmealfoodapp.data.model.Meal
import com.sunasterisk.dmealfoodapp.databinding.ItemMealBinding
import com.sunasterisk.dmealfoodapp.utils.loadImage

class MealListAdapter(private val onItemClick: (Meal) -> Unit) :
    BaseAdapter<Meal, MealListAdapter.MealListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealListViewHolder =
        MealListViewHolder(
            ItemMealBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ), onItemClick
        )

    class MealListViewHolder(
        private val binding: ItemMealBinding,
        onItemClick: (Meal) -> Unit
    ) : BaseViewHolder<Meal>(binding, onItemClick) {

        override fun onBindData(itemData: Meal) {
            super.onBindData(itemData)
            with(binding) {
                itemData.apply {
                    textNameMeal.text = name
                    itemData.image.let {
                        imageMeal.loadImage(it)
                    }
                }
            }
        }
    }
}
