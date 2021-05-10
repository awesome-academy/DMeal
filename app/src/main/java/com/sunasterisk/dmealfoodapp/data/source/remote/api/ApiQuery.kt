package com.sunasterisk.dmealfoodapp.data.source.remote.api

import android.net.Uri
import com.sunasterisk.dmealfoodapp.data.model.Ingredient

object ApiQuery {
    fun queryMealCategory() = Uri.Builder().scheme(ApiConstants.SCHEME_HTTPS)
        .authority(ApiConstants.AUTHORITY_API)
        .appendPath(ApiConstants.CONTENT)
        .appendPath(ApiConstants.MEAL_CATEGORY)
        .toString()

    fun queryMealByCategory(meal: String) = Uri.Builder().scheme(ApiConstants.SCHEME_HTTPS)
        .authority(ApiConstants.AUTHORITY_API)
        .appendPath(ApiConstants.CONTENT)
        .appendPath(ApiConstants.FILTER)
        .appendQueryParameter(ApiConstants.FILTER_CATEGORY_MEAL, meal)
        .toString()

    fun queryMealDetail(idMeal: String) = Uri.Builder().scheme(ApiConstants.SCHEME_HTTPS)
        .authority(ApiConstants.AUTHORITY_API)
        .appendPath(ApiConstants.CONTENT)
        .appendPath(ApiConstants.LOOKUP)
        .appendQueryParameter(ApiConstants.FILTER_LOOKUP, idMeal)
        .toString()

    fun querySearchMeal(wordSearch: String) = Uri.Builder().scheme(ApiConstants.SCHEME_HTTPS)
        .authority(ApiConstants.AUTHORITY_API)
        .appendPath(ApiConstants.CONTENT)
        .appendPath(ApiConstants.SEARCH)
        .appendQueryParameter(ApiConstants.FILTER_SEARCH_MEAL, wordSearch)
        .toString()

    fun queryMealByIngredient(meal: String) = Uri.Builder().scheme(ApiConstants.SCHEME_HTTPS)
        .authority(ApiConstants.AUTHORITY_API)
        .appendPath(ApiConstants.CONTENT)
        .appendPath(ApiConstants.FILTER)
        .appendQueryParameter(ApiConstants.FILTER_INGREDIENT, meal)
        .toString()

    fun queryIngredient() = Uri.Builder().scheme(ApiConstants.SCHEME_HTTPS)
        .authority(ApiConstants.AUTHORITY_API)
        .appendPath(ApiConstants.CONTENT)
        .appendPath(ApiConstants.LIST)
        .appendQueryParameter(ApiConstants.FILTER_INGREDIENT, ApiConstants.VALUE_LIST)
        .toString()

    fun queryImageIngredient(nameIngredient: String) =
        Uri.Builder().scheme(ApiConstants.SCHEME_HTTPS)
            .authority(ApiConstants.AUTHORITY_API)
            .appendPath(ApiConstants.CONTENT_IMAGE)
            .appendPath(nameIngredient + ApiConstants.PNG)
            .toString()
}
