package com.sunasterisk.dmealfoodapp.ui.ingredient

import com.sunasterisk.dmealfoodapp.base.BasePresenter
import com.sunasterisk.dmealfoodapp.base.BaseView
import com.sunasterisk.dmealfoodapp.data.model.Ingredient

interface IngredientContact {
    interface View : BaseView {
        fun showIngredients(ingredients: List<Ingredient>)
    }

    interface Presenter : BasePresenter {
        fun getListIngredient()
    }
}
