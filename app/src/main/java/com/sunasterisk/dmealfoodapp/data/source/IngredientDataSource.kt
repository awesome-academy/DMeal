package com.sunasterisk.dmealfoodapp.data.source

import com.sunasterisk.dmealfoodapp.data.model.Ingredient
import com.sunasterisk.dmealfoodapp.data.source.ultils.OnDataCallback

interface IngredientDataSource {
    interface Remote {
        fun getIngredients(callback: OnDataCallback<List<Ingredient>>)
    }

    interface Local{
        fun insertIngredient(ingredient: Ingredient, callback: OnDataCallback<Long>)
        fun deleteIngredient(ingredientId: String, callback: OnDataCallback<Boolean>)
        fun getAllIngredients(callback: OnDataCallback<List<Ingredient>>)
        fun isFavorite(ingredientId: String, callback: OnDataCallback<Int>)
    }
}
