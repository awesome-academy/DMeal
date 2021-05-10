package com.sunasterisk.dmealfoodapp.utils

import com.sunasterisk.dmealfoodapp.R
import com.sunasterisk.dmealfoodapp.data.model.Ingredient
import com.sunasterisk.dmealfoodapp.data.model.Meal
import com.sunasterisk.dmealfoodapp.data.model.MealCategory
import com.sunasterisk.dmealfoodapp.data.model.MealDetail
import org.json.JSONArray
import org.json.JSONException

inline fun <reified T> String.parseJsonToObject() = JSONArray(this).run {
    List(length()) { index ->
        when (T::class) {
            Ingredient::class -> Ingredient(getJSONObject(index)) as T
            Meal::class -> Meal(getJSONObject(index)) as T
            MealCategory::class -> MealCategory(getJSONObject(index)) as T
            MealDetail::class -> MealDetail(getJSONObject(index)) as T
            else -> throw JSONException(R.string.error.toString())
        }
    }
}
