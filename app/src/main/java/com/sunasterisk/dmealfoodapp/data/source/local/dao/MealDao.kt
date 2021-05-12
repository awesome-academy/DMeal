package com.sunasterisk.dmealfoodapp.data.source.local.dao

import com.sunasterisk.dmealfoodapp.data.model.Meal

interface MealDao {
    fun insertMeal(meal: Meal): Long
    fun deleteMeal(idMeal: String): Boolean
    fun getAllMeals(): List<Meal>
    fun isFavorite(mealId: String): Int
}
