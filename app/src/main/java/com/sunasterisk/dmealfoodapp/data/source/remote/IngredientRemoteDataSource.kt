package com.sunasterisk.dmealfoodapp.data.source.remote

import com.sunasterisk.dmealfoodapp.data.model.Ingredient
import com.sunasterisk.dmealfoodapp.data.source.IngredientDataSource
import com.sunasterisk.dmealfoodapp.data.source.remote.api.ApiQuery
import com.sunasterisk.dmealfoodapp.data.source.remote.utils.connectDownloadData
import com.sunasterisk.dmealfoodapp.data.source.ultils.LoadDataAsync
import com.sunasterisk.dmealfoodapp.data.source.ultils.OnDataCallback
import com.sunasterisk.dmealfoodapp.utils.Constants
import com.sunasterisk.dmealfoodapp.utils.parseJsonToObject
import org.json.JSONObject

@Suppress("DEPRECATION")
class IngredientRemoteDataSource private constructor() : IngredientDataSource.Remote {

    override fun getIngredients(callback: OnDataCallback<List<Ingredient>>) {
        LoadDataAsync<Unit, List<Ingredient>>(callback) {
            getIngredients()
        }.execute(Unit)
    }

    private fun getIngredients(): List<Ingredient> =
        JSONObject(connectDownloadData(ApiQuery.queryIngredient()))
            .getString(Constants.MEALS).parseJsonToObject()

    companion object {

        private var instance: IngredientRemoteDataSource? = null
        fun getInstance() =
            instance ?: IngredientRemoteDataSource().also { instance = it }
    }
}
