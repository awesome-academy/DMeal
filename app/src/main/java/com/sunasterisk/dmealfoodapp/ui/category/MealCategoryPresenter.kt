package com.sunasterisk.dmealfoodapp.ui.category

import com.sunasterisk.dmealfoodapp.data.model.MealCategory
import com.sunasterisk.dmealfoodapp.data.repository.MealCategoryRepository
import com.sunasterisk.dmealfoodapp.data.source.ultils.OnDataCallback

class MealCategoryPresenter(
    private val view: MealCategoryContact.View,
    private val mealCategoryRepository: MealCategoryRepository
) : MealCategoryContact.Presenter {

    override fun getMealCategories() {
        view.showLoading()
        mealCategoryRepository.getMealCategories(object : OnDataCallback<List<MealCategory>> {
            override fun onSuccess(data: List<MealCategory>) {
                view.showMealCategories(data)
                view.hideLoading()
            }

            override fun onFailure(throwable: Throwable) {
                view.showMessage(throwable.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun start() {
        getMealCategories()
    }
}
