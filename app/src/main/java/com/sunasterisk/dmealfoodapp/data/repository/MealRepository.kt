package com.sunasterisk.dmealfoodapp.data.repository

import com.sunasterisk.dmealfoodapp.data.model.Meal
import com.sunasterisk.dmealfoodapp.data.source.MealDataSource
import com.sunasterisk.dmealfoodapp.data.source.ultils.OnDataCallback

class MealRepository private constructor(
    private val remote: MealDataSource.Remote
) : MealDataSource.Remote {

    override fun getMealByCategory(nameCategory: String, callback: OnDataCallback<List<Meal>>) {
        remote.getMealByCategory(nameCategory, callback)
    }

    override fun getMealByIngredient(nameIngredient: String, callback: OnDataCallback<List<Meal>>) {
        remote.getMealByIngredient(nameIngredient, callback)
    }

    override fun searchMeal(nameMeal: String, callback: OnDataCallback<List<Meal>>) {
        remote.searchMeal(nameMeal, callback)
    }

    override fun getMealDetailByMeal(nameMeal: String, callback: OnDataCallback<List<Meal>>) {
        remote.getMealDetailByMeal(nameMeal, callback)
    }

    companion object {

        private var instance: MealRepository? = null
        fun getInstance(remote: MealDataSource.Remote) =
            instance ?: MealRepository(remote).also { instance = it }
    }
}
