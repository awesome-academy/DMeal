package com.sunasterisk.dmealfoodapp.ui.detailmeal

import com.sunasterisk.dmealfoodapp.data.model.Meal
import com.sunasterisk.dmealfoodapp.data.model.MealDetail
import com.sunasterisk.dmealfoodapp.data.repository.MealRepository
import com.sunasterisk.dmealfoodapp.data.source.ultils.OnDataCallback

class MealDetailPresenter(
    private val view: MealDetailContact.View,
    private val repository: MealRepository
) : MealDetailContact.Presenter {

    override fun getMealDetailByMeal(meal: Meal) {
        view.showLoading()
        repository.getMealDetailByMeal(meal.id, object : OnDataCallback<List<MealDetail>> {
            override fun onSuccess(data: List<MealDetail>) {
                view.showMealDetailByMeal(data)
                view.hideLoading()
            }

            override fun onFailure(throwable: Throwable) {
                view.showMessage(throwable.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun getMealFavorite(meal: Meal) {
        view.showLoading()
        repository.isFavorite(meal.id, object : OnDataCallback<Int> {
            override fun onSuccess(data: Int) {
                view.showMealFavorite(data)
                view.hideLoading()
            }

            override fun onFailure(throwable: Throwable) {
                view.showMessage(throwable.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun insertMealFavorite(meal: Meal) {
        view.showLoading()
        repository.insertMeal(meal, object : OnDataCallback<Long> {
            override fun onSuccess(data: Long) {
                if (data > 0) view.isInsertedMealFavorite(data)
                view.hideLoading()
            }

            override fun onFailure(throwable: Throwable) {
                view.showMessage(throwable.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun deleteMealFavorite(mealId: String) {
        view.showLoading()
        repository.deleteMeal(mealId, object : OnDataCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                if (data) view.isDeletedMealFavorite(data)
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
