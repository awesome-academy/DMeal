package com.sunasterisk.dmealfoodapp.ui.search

import com.sunasterisk.dmealfoodapp.base.BasePresenter
import com.sunasterisk.dmealfoodapp.base.BaseView
import com.sunasterisk.dmealfoodapp.data.model.Meal

interface SearchContact {
    interface View : BaseView {
        fun showResult(meals: List<Meal>)
        fun showNotFound()
        fun hideNotFound()
    }

    interface Presenter : BasePresenter {
        fun getMealsByKeySearch(keySearch: String)
    }
}
