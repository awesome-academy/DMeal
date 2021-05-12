package com.sunasterisk.dmealfoodapp.data.repository

import com.sunasterisk.dmealfoodapp.data.model.Ingredient
import com.sunasterisk.dmealfoodapp.data.source.IngredientDataSource
import com.sunasterisk.dmealfoodapp.data.source.ultils.OnDataCallback

class IngredientRepository private constructor(
    private val remote: IngredientDataSource.Remote
) : IngredientDataSource.Remote {

    override fun getIngredients(callback: OnDataCallback<List<Ingredient>>) {
        remote.getIngredients(callback)
    }

    companion object {

        private var instance: IngredientRepository? = null
        fun getInstance(remote: IngredientDataSource.Remote) =
            instance ?: IngredientRepository(remote).also { instance = it }
    }
}
