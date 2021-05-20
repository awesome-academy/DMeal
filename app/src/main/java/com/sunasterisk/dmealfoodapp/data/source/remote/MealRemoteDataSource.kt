package com.sunasterisk.dmealfoodapp.data.source.remote

import com.sunasterisk.dmealfoodapp.data.model.Meal
import com.sunasterisk.dmealfoodapp.data.model.MealDetail
import com.sunasterisk.dmealfoodapp.data.source.MealDataSource
import com.sunasterisk.dmealfoodapp.data.source.remote.api.ApiConstants
import com.sunasterisk.dmealfoodapp.data.source.remote.api.ApiQuery
import com.sunasterisk.dmealfoodapp.data.source.remote.utils.connectDownloadData
import com.sunasterisk.dmealfoodapp.data.source.ultils.LoadDataAsync
import com.sunasterisk.dmealfoodapp.data.source.ultils.OnDataCallback
import com.sunasterisk.dmealfoodapp.utils.Constants
import com.sunasterisk.dmealfoodapp.utils.parseJsonToObject
import org.json.JSONObject

@Suppress("DEPRECATION")
class MealRemoteDataSource private constructor() : MealDataSource.Remote {

    override fun getMealByCategory(nameCategory: String, callback: OnDataCallback<List<Meal>>) {
        LoadDataAsync<Unit, List<Meal>>(callback) {
            getMealByCategory(nameCategory)
        }.execute(Unit)
    }

    override fun getMealByIngredient(nameIngredient: String, callback: OnDataCallback<List<Meal>>) {
        LoadDataAsync<Unit, List<Meal>>(callback) {
            getMealByIngredient(nameIngredient)
        }.execute(Unit)
    }

    override fun searchMeal(nameMeal: String, callback: OnDataCallback<List<Meal>>) {
        LoadDataAsync<Unit, List<Meal>>(callback) {
            getMealByKeySearch(nameMeal)
        }.execute(Unit)
    }

    override fun getMealDetailByMeal(nameMeal: String, callback: OnDataCallback<List<MealDetail>>) {
        LoadDataAsync<Unit, List<MealDetail>>(callback) {
            getMealDetailByMeal(nameMeal)
        }.execute(Unit)
    }

    private fun getMealByCategory(nameCategory: String): List<Meal> =
        JSONObject(connectDownloadData(ApiQuery.queryMealByCategory(nameCategory)))
            .getString(Constants.MEALS).parseJsonToObject()

    private fun getMealByIngredient(nameIngredient: String): List<Meal> =
        JSONObject(connectDownloadData(ApiQuery.queryMealByIngredient(nameIngredient)))
            .getString(Constants.MEALS).parseJsonToObject()

    private fun getMealDetailByMeal(idMeal: String): List<MealDetail> =
        JSONObject(connectDownloadData(ApiQuery.queryMealDetail(idMeal)))
            .getString(Constants.MEALS).parseJsonToObject()

    private fun getMealByKeySearch(nameMeal: String): List<Meal> {
        val json = connectDownloadData(ApiQuery.querySearchMeal(nameMeal))
        return if (json == ApiConstants.MEAL_NULL) {
            listOf()
        } else {
            JSONObject(json).getString(Constants.MEALS).parseJsonToObject()
        }
    }

    companion object {

        private var instance: MealRemoteDataSource? = null
        fun getInstance() =
            instance ?: MealRemoteDataSource().also { instance = it }
    }
}
