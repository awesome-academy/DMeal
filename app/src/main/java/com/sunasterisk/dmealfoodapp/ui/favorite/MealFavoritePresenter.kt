package com.sunasterisk.dmealfoodapp.ui.favorite

import com.sunasterisk.dmealfoodapp.data.model.Meal
import com.sunasterisk.dmealfoodapp.data.repository.MealRepository
import com.sunasterisk.dmealfoodapp.data.source.ultils.OnDataCallback

class MealFavoritePresenter(
    private val view: MealFavoriteContact.View,
    private val repository: MealRepository
) : MealFavoriteContact.Presenter {

    override fun getMealsFavorite() {
        view.showLoading()
        repository.getAllMeals(object : OnDataCallback<List<Meal>> {
            override fun onSuccess(data: List<Meal>) {
                view.showMealsFavorite(data)
                view.hideLoading()
            }

            override fun onFailure(throwable: Throwable) {
                view.showMessage(throwable.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun start() {
        getMealsFavorite()
    }
}
