package com.sunasterisk.dmealfoodapp.ui.search

import com.sunasterisk.dmealfoodapp.data.model.Meal
import com.sunasterisk.dmealfoodapp.data.repository.MealRepository
import com.sunasterisk.dmealfoodapp.data.source.ultils.OnDataCallback

class SearchPresenter(
    private val view: SearchContact.View,
    private val repository: MealRepository
) : SearchContact.Presenter {

    override fun getMealsByKeySearch(keySearch: String) {
        view.showLoading()
        repository.searchMeal(keySearch, object : OnDataCallback<List<Meal>> {
            override fun onSuccess(data: List<Meal>) {
                if (data.isEmpty()) {
                    view.showNotFound()
                } else {
                    view.hideNotFound()
                }
                view.hideLoading()
                view.showResult(data)
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
