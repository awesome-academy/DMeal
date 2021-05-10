package com.sunasterisk.dmealfoodapp.data.source.remote.api

object ApiConstants {
    const val SCHEME_HTTPS = "https"
    const val AUTHORITY_API = "www.themealdb.com"
    const val CONTENT = "api/json/v1/1"
    const val CONTENT_IMAGE = "images/ingredients"

    const val MEAL_CATEGORY = "categories.php"
    const val LIST = "list.php"
    const val SEARCH = "search.php"
    const val LOOKUP = "lookup.php"
    const val FILTER = "filter.php"

    const val FILTER_SEARCH_MEAL = "s"
    const val FILTER_LOOKUP = "i"
    const val FILTER_CATEGORY_MEAL = "c"
    const val FILTER_INGREDIENT = "i"
    const val VALUE_LIST = "list"
    const val MEAL_NULL = "{\"meals\":null}"
    const val PNG = ".png"
}
