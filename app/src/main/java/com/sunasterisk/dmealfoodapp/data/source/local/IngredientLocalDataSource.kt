package com.sunasterisk.dmealfoodapp.data.source.local

import com.sunasterisk.dmealfoodapp.data.model.Ingredient
import com.sunasterisk.dmealfoodapp.data.source.IngredientDataSource
import com.sunasterisk.dmealfoodapp.data.source.local.dao.IngredientDao
import com.sunasterisk.dmealfoodapp.data.source.ultils.LoadDataAsync
import com.sunasterisk.dmealfoodapp.data.source.ultils.OnDataCallback

@Suppress("DEPRECATION")
class IngredientLocalDataSource private constructor(
    private val ingredientDao: IngredientDao
) : IngredientDataSource.Local {

    override fun insertIngredient(ingredient: Ingredient, callback: OnDataCallback<Long>) {
        LoadDataAsync<Ingredient, Long>(callback) {
            ingredientDao.insertIngredient(ingredient)
        }.execute(ingredient)
    }

    override fun deleteIngredient(ingredientId: String, callback: OnDataCallback<Boolean>) {
        LoadDataAsync<String, Boolean>(callback) {
            ingredientDao.deleteIngredient(ingredientId)
        }.execute(ingredientId)
    }

    override fun getAllIngredients(callback: OnDataCallback<List<Ingredient>>) {
        LoadDataAsync<Unit, List<Ingredient>>(callback) {
            ingredientDao.getAllIngredients()
        }.execute()
    }

    override fun isFavorite(ingredientId: String, callback: OnDataCallback<Int>) {
        LoadDataAsync<String, Int>(callback) {
            ingredientDao.isFavorite(ingredientId)
        }.execute(ingredientId)
    }

    companion object {
        private var instance: IngredientLocalDataSource? = null

        fun getInstance(ingredientDao: IngredientDao) =
            instance ?: IngredientLocalDataSource(ingredientDao).also { instance = it }
    }
}
