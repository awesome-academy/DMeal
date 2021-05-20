package com.sunasterisk.dmealfoodapp.ui.ingredient

import com.sunasterisk.dmealfoodapp.data.model.Ingredient
import com.sunasterisk.dmealfoodapp.data.repository.IngredientRepository
import com.sunasterisk.dmealfoodapp.data.source.ultils.OnDataCallback

class IngredientPresenter(
    private val view: IngredientContact.View,
    private val repository: IngredientRepository
) : IngredientContact.Presenter {

    override fun getListIngredient() {
        view.showLoading()
        repository.getIngredients(object : OnDataCallback<List<Ingredient>> {
            override fun onSuccess(data: List<Ingredient>) {
                view.showIngredients(data)
                view.hideLoading()
            }

            override fun onFailure(throwable: Throwable) {
                view.showMessage(throwable.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun start() {
        getListIngredient()
    }
}
