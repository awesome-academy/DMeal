package com.sunasterisk.dmealfoodapp.data.repository

import com.sunasterisk.dmealfoodapp.data.model.Ingredient
import com.sunasterisk.dmealfoodapp.data.source.IngredientDataSource
import com.sunasterisk.dmealfoodapp.data.source.ultils.OnDataCallback

class IngredientRepository private constructor(
    private val remote: IngredientDataSource.Remote,
    private val local: IngredientDataSource.Local
) : IngredientDataSource.Remote, IngredientDataSource.Local {

    override fun getIngredients(callback: OnDataCallback<List<Ingredient>>) {
        remote.getIngredients(callback)
    }

    override fun insertIngredient(ingredient: Ingredient, callback: OnDataCallback<Long>) {
        local.insertIngredient(ingredient, callback)
    }

    override fun deleteIngredient(ingredientId: String, callback: OnDataCallback<Boolean>) {
        local.deleteIngredient(ingredientId, callback)
    }

    override fun getAllIngredients(callback: OnDataCallback<List<Ingredient>>) {
        local.getAllIngredients(callback)
    }

    override fun isFavorite(ingredientId: String, callback: OnDataCallback<Int>) {
        local.isFavorite(ingredientId, callback)
    }

    companion object {

        private var instance: IngredientRepository? = null
        fun getInstance(remote: IngredientDataSource.Remote, local: IngredientDataSource.Local) =
            instance ?: IngredientRepository(remote, local).also { instance = it }
    }
}
