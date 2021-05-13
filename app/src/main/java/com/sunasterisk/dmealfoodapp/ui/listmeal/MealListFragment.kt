package com.sunasterisk.dmealfoodapp.ui.listmeal

import androidx.core.os.bundleOf
import com.sunasterisk.dmealfoodapp.R
import com.sunasterisk.dmealfoodapp.base.BaseFragment

class MealListFragment : BaseFragment(R.layout.fragment_meal_list) {

    override fun onCreatedView() {
    }

    override fun initData() {
    }

    override fun initActions() {
    }

    companion object {
        const val MEAL_CATEGORY_ID = "MEAL_CATEGORY_ID"

        fun getInstance(idMealCategory: Any) = MealListFragment().apply {
            arguments = bundleOf(MEAL_CATEGORY_ID to idMealCategory)
        }
    }
}
