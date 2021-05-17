package com.sunasterisk.dmealfoodapp.ui.detailmeal

import androidx.core.os.bundleOf
import com.sunasterisk.dmealfoodapp.R
import com.sunasterisk.dmealfoodapp.base.BaseFragment
import com.sunasterisk.dmealfoodapp.data.model.Meal

class MealDetailFragment : BaseFragment(R.layout.fragment_meal_detail) {

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
