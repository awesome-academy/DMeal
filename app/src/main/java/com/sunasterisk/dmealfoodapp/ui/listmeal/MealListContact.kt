package com.sunasterisk.dmealfoodapp.ui.listmeal

import com.sunasterisk.dmealfoodapp.base.BasePresenter
import com.sunasterisk.dmealfoodapp.base.BaseView
import com.sunasterisk.dmealfoodapp.data.model.Meal
import com.sunasterisk.dmealfoodapp.data.model.MealCategory

interface MealListContact {
    interface View : BaseView {
        fun showListMeal(listMeal: List<Meal>)
    }

    interface Presenter : BasePresenter {
        fun getListMealByCategory(mealCategory: MealCategory)
    }
}
