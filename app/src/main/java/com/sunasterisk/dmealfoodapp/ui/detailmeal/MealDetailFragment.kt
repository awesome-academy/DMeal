package com.sunasterisk.dmealfoodapp.ui.detailmeal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.sunasterisk.dmealfoodapp.R
import com.sunasterisk.dmealfoodapp.base.BaseFragment
import com.sunasterisk.dmealfoodapp.data.model.Meal
import com.sunasterisk.dmealfoodapp.databinding.FragmentMealDetailBinding

class MealDetailFragment : BaseFragment<FragmentMealDetailBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMealDetailBinding =
        FragmentMealDetailBinding::inflate

    override fun onCreatedView() {

    }

    override fun initData() {

    }

    override fun initActions() {

    }

    companion object {
        const val MEAL = "MEAL"

        fun getInstance(meal: Meal) = MealDetailFragment().apply {
            arguments = bundleOf(MEAL to meal)
        }
    }
}
