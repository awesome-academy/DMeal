package com.sunasterisk.dmealfoodapp.ui.ingredientdetail

import com.sunasterisk.dmealfoodapp.data.model.Ingredient
import com.sunasterisk.dmealfoodapp.data.model.Meal
import com.sunasterisk.dmealfoodapp.data.repository.MealRepository
import com.sunasterisk.dmealfoodapp.data.source.ultils.OnDataCallback

class IngredientDetailPresenter(
    private val view: IngredientDetailContact.View,
    private val repository: MealRepository
) : IngredientDetailContact.Presenter {

    override fun getMealsByIngredient(ingredient: Ingredient) {
        view.showLoading()
        repository.getMealByIngredient(ingredient.name, object : OnDataCallback<List<Meal>> {
            override fun onSuccess(data: List<Meal>) {
                view.showMeals(data)
                view.hideLoading()
            }

            override fun onFailure(throwable: Throwable) {
                view.showMessage(throwable.message.toString())
                view.hideLoading()
            }

        })
    }

    override fun start() {
    }
}
