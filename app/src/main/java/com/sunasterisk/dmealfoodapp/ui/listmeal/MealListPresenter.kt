package com.sunasterisk.dmealfoodapp.ui.listmeal

import com.sunasterisk.dmealfoodapp.data.model.Meal
import com.sunasterisk.dmealfoodapp.data.model.MealCategory
import com.sunasterisk.dmealfoodapp.data.repository.MealRepository
import com.sunasterisk.dmealfoodapp.data.source.ultils.OnDataCallback

class MealListPresenter(
    private val view: MealListContact.View,
    private val mealListRepository: MealRepository
) : MealListContact.Presenter {

    override fun getListMealByCategory(mealCategory: MealCategory) {
        view.showLoading()
        mealListRepository.getMealByCategory(
            mealCategory.name,
            object : OnDataCallback<List<Meal>> {
                override fun onSuccess(data: List<Meal>) {
                    view.showListMeal(data)
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
