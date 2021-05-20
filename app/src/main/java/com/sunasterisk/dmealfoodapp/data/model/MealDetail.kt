package com.sunasterisk.dmealfoodapp.data.model

import android.os.Parcelable
import com.sunasterisk.dmealfoodapp.utils.MealDetailConst
import com.sunasterisk.dmealfoodapp.utils.MealModelConst.STR_MEAL
import com.sunasterisk.dmealfoodapp.utils.MealModelConst.STR_MEAL_THUMB
import kotlinx.parcelize.Parcelize
import org.json.JSONObject

@Parcelize
data class MealDetail(
    val name: String,
    val category: String,
    val instruction: String,
    val youtube: String,
    val image: String,
    val ingredient: String = ""
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(STR_MEAL),
        jsonObject.getString(MealDetailConst.MEAL_DETAIL_CATEGORY),
        jsonObject.getString(MealDetailConst.MEAL_DETAIL_INSTRUCTIONS),
        jsonObject.getString(MealDetailConst.MEAL_DETAIL_YOUTUBE),
        jsonObject.getString(STR_MEAL_THUMB),
        getIngredientIndex(MealDetailConst.MEAL_DETAIL_INGREDIENT, jsonObject)
    )

    companion object {
        fun getIngredientIndex(key: String, jsonObject: JSONObject) = StringBuilder().run {
            for (i in 1..20) {
                val ingredient = jsonObject.getString("$key$i")
                if (ingredient.isEmpty() || ingredient == "null") break
                append(ingredient).append('\n')
            }
            substring(0, (length - 2))
        }
    }
}

