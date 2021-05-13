package com.sunasterisk.dmealfoodapp.ui.category

import com.sunasterisk.dmealfoodapp.base.BasePresenter
import com.sunasterisk.dmealfoodapp.base.BaseView
import com.sunasterisk.dmealfoodapp.data.model.MealCategory

interface MealCategoryContact {
    interface View : BaseView {
        fun showMealCategories(mealCategories: List<MealCategory>)
    }

    interface Presenter : BasePresenter {
        fun getMealCategories()
    }
}
