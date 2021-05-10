package com.sunasterisk.dmealfoodapp.data.source

import com.sunasterisk.dmealfoodapp.data.model.Ingredient
import com.sunasterisk.dmealfoodapp.data.source.ultils.OnDataCallback

interface IngredientDataSource {
    interface Remote {
        fun getIngredients(callback: OnDataCallback<List<Ingredient>>)
    }
}
