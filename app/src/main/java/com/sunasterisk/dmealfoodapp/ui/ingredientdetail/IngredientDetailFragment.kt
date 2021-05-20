package com.sunasterisk.dmealfoodapp.ui.ingredientdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.sunasterisk.dmealfoodapp.base.BaseFragment
import com.sunasterisk.dmealfoodapp.data.model.Ingredient
import com.sunasterisk.dmealfoodapp.databinding.FragmentIngredientDetailBinding

class IngredientDetailFragment : BaseFragment<FragmentIngredientDetailBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentIngredientDetailBinding =
        FragmentIngredientDetailBinding::inflate

    override fun onCreatedView() {
    }

    override fun initData() {
    }

    override fun initActions() {
    }

    companion object {
        private const val BUNDLE_INGREDIENT = "BUNDLE_INGREDIENT"

        fun getInstance(ingredient: Ingredient) = IngredientDetailFragment().apply {
            arguments = bundleOf(BUNDLE_INGREDIENT to ingredient)
        }
    }
}
