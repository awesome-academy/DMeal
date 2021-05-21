package com.sunasterisk.dmealfoodapp.ui.favorite

import com.sunasterisk.dmealfoodapp.base.BasePresenter
import com.sunasterisk.dmealfoodapp.base.BaseView
import com.sunasterisk.dmealfoodapp.data.model.Meal

interface MealFavoriteContact {
    interface View : BaseView {
        fun showMealsFavorite(meals: List<Meal>)
    }

    interface Presenter : BasePresenter {
        fun getMealsFavorite()
    }
}
