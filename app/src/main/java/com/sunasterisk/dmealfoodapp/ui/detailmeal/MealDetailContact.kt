package com.sunasterisk.dmealfoodapp.ui.detailmeal

import com.sunasterisk.dmealfoodapp.base.BasePresenter
import com.sunasterisk.dmealfoodapp.base.BaseView
import com.sunasterisk.dmealfoodapp.data.model.Meal
import com.sunasterisk.dmealfoodapp.data.model.MealDetail

interface MealDetailContact {
    interface View : BaseView {
        fun showMealDetailByMeal(mealDetails: List<MealDetail>)
        fun showMealFavorite(isFavorite: Int)
        fun isInsertedMealFavorite(long: Long)
        fun isDeletedMealFavorite(boolean: Boolean)
    }

    interface Presenter : BasePresenter {
        fun getMealDetailByMeal(meal: Meal)
        fun getMealFavorite(meal: Meal)
        fun insertMealFavorite(meal: Meal)
        fun deleteMealFavorite(mealId: String)
    }
}
