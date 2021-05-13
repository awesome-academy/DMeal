package com.sunasterisk.dmealfoodapp.data.source.local.dao

import com.sunasterisk.dmealfoodapp.data.model.Ingredient

interface IngredientDao {
    fun insertIngredient(ingredient: Ingredient): Long
    fun deleteIngredient(idIngredient: String): Boolean
    fun getAllIngredients(): List<Ingredient>
    fun isFavorite(ingredientId: String): Int
}
