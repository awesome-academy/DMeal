package com.sunasterisk.dmealfoodapp.data.source.remote

import com.sunasterisk.dmealfoodapp.data.model.MealCategory
import com.sunasterisk.dmealfoodapp.data.source.MealCategoryDataSource
import com.sunasterisk.dmealfoodapp.data.source.remote.api.ApiQuery
import com.sunasterisk.dmealfoodapp.data.source.remote.utils.connectDownloadData
import com.sunasterisk.dmealfoodapp.data.source.ultils.LoadDataAsync
import com.sunasterisk.dmealfoodapp.data.source.ultils.OnDataCallback
import com.sunasterisk.dmealfoodapp.utils.Constants
import com.sunasterisk.dmealfoodapp.utils.parseJsonToObject
import org.json.JSONObject

@Suppress("DEPRECATION")
class MealCategoryRemoteDataSource private constructor() : MealCategoryDataSource {

    override fun getMealCategories(callback: OnDataCallback<List<MealCategory>>) {
        LoadDataAsync<Unit, List<MealCategory>>(callback) {
            getMealCategories()
        }.execute(Unit)
    }

    private fun getMealCategories(): List<MealCategory> =
        JSONObject(connectDownloadData(ApiQuery.queryMealCategory()))
            .getString(Constants.CATEGORIES)
            .parseJsonToObject()

    companion object {

        private var instance: MealCategoryRemoteDataSource? = null
        fun getInstance() =
            instance ?: MealCategoryRemoteDataSource().also { instance = it }
    }
}
