package com.sunasterisk.dmealfoodapp.ui.ingredientdetail

import com.sunasterisk.dmealfoodapp.base.BasePresenter
import com.sunasterisk.dmealfoodapp.base.BaseView
import com.sunasterisk.dmealfoodapp.data.model.Ingredient
import com.sunasterisk.dmealfoodapp.data.model.Meal

interface IngredientDetailContact {
    interface View : BaseView {
        fun showMeals(meals: List<Meal>)
    }

    interface Presenter : BasePresenter {
        fun getMealsByIngredient(ingredient: Ingredient)
    }
}
