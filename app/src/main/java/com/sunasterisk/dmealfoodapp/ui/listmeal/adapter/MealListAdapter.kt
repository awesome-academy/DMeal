package com.sunasterisk.dmealfoodapp.ui.listmeal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sunasterisk.dmealfoodapp.R
import com.sunasterisk.dmealfoodapp.base.BaseAdapter
import com.sunasterisk.dmealfoodapp.base.BaseViewHolder
import com.sunasterisk.dmealfoodapp.data.model.Meal
import com.sunasterisk.dmealfoodapp.utils.loadImage
import kotlinx.android.synthetic.main.item_meal.view.*

class MealListAdapter(private val onItemClick: (Meal) -> Unit) :
    BaseAdapter<Meal, MealListAdapter.MealListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealListViewHolder =
        MealListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_meal, parent, false),
            onItemClick
        )

    class MealListViewHolder(
        itemView: View,
        onItemClick: (Meal) -> Unit
    ) : BaseViewHolder<Meal>(itemView, onItemClick) {

        override fun onBindData(itemData: Meal) {
            super.onBindData(itemData)
            with(itemView) {
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
