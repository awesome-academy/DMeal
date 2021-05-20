package com.sunasterisk.dmealfoodapp.ui.ingredient.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sunasterisk.dmealfoodapp.base.BaseAdapter
import com.sunasterisk.dmealfoodapp.base.BaseViewHolder
import com.sunasterisk.dmealfoodapp.data.model.Ingredient
import com.sunasterisk.dmealfoodapp.databinding.ItemIngredientBinding

class IngredientAdapter(private val onItemClick: (Ingredient) -> Unit) :
    BaseAdapter<Ingredient, IngredientAdapter.IngredientViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientViewHolder =
        IngredientViewHolder(
            ItemIngredientBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onItemClick
        )

    class IngredientViewHolder(
        private val binding: ItemIngredientBinding,
        onItemClick: (Ingredient) -> Unit
    ) : BaseViewHolder<Ingredient>(binding, onItemClick) {
        override fun onBindData(itemData: Ingredient) {
            super.onBindData(itemData)
            with(binding) {
                itemData.apply {
                    textIngredient.text = name
                    textContent.text = description
                }
            }
        }
    }
}
