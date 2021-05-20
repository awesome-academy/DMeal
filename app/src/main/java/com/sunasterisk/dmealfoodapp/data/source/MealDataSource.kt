package com.sunasterisk.dmealfoodapp.data.source

import com.sunasterisk.dmealfoodapp.data.model.Ingredient
import com.sunasterisk.dmealfoodapp.data.model.Meal
import com.sunasterisk.dmealfoodapp.data.model.MealDetail
import com.sunasterisk.dmealfoodapp.data.source.ultils.OnDataCallback

interface MealDataSource {
    interface Remote {
        fun getMealByCategory(nameCategory: String, callback: OnDataCallback<List<Meal>>)
        fun getMealByIngredient(nameIngredient: String, callback: OnDataCallback<List<Meal>>)
        fun searchMeal(nameMeal: String, callback: OnDataCallback<List<Meal>>)
        fun getMealDetailByMeal(nameMeal: String, callback: OnDataCallback<List<MealDetail>>)
    }

    interface Local{
        fun insertMeal(meal: Meal, callback: OnDataCallback<Long>)
        fun deleteMeal(mealId: String, callback: OnDataCallback<Boolean>)
        fun getAllMeals(callback: OnDataCallback<List<Meal>>)
        fun isFavorite(mealId: String, callback: OnDataCallback<Int>)
    }
}
