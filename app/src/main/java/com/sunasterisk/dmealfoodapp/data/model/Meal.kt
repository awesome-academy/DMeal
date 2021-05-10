package com.sunasterisk.dmealfoodapp.data.model

import android.os.Parcelable
import com.sunasterisk.dmealfoodapp.utils.MealModelConst.ID_MEAL
import com.sunasterisk.dmealfoodapp.utils.MealModelConst.STR_MEAL
import com.sunasterisk.dmealfoodapp.utils.MealModelConst.STR_MEAL_THUMB
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Meal(
    val id : String,
    val name: String,
    val image: String
) : Parcelable{
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(ID_MEAL),
        jsonObject.getString(STR_MEAL),
        jsonObject.getString(STR_MEAL_THUMB)
    )
}
