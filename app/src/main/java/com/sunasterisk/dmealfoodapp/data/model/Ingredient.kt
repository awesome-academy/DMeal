package com.sunasterisk.dmealfoodapp.data.model

import android.os.Parcelable
import com.sunasterisk.dmealfoodapp.utils.IngredientModelConst.ID_INGREDIENT
import com.sunasterisk.dmealfoodapp.utils.IngredientModelConst.STR_DESCRIPTION
import com.sunasterisk.dmealfoodapp.utils.IngredientModelConst.STR_INGREDIENT
import com.sunasterisk.dmealfoodapp.utils.IngredientModelConst.STR_TYPE
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Ingredient(
    val id: String,
    val name: String,
    val description: String?,
    val type: String
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(ID_INGREDIENT),
        jsonObject.getString(STR_INGREDIENT),
        jsonObject.getString(STR_DESCRIPTION),
        jsonObject.getString(STR_TYPE)
    )
}
