package com.sunasterisk.dmealfoodapp.data.model

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import com.sunasterisk.dmealfoodapp.utils.IngredientModelConst.ID_INGREDIENT
import com.sunasterisk.dmealfoodapp.utils.IngredientModelConst.STR_DESCRIPTION
import com.sunasterisk.dmealfoodapp.utils.IngredientModelConst.STR_INGREDIENT
import kotlinx.parcelize.Parcelize
import org.json.JSONObject

@Parcelize
data class Ingredient(
    val id: String,
    val name: String,
    val description: String?,
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(ID_INGREDIENT),
        jsonObject.getString(STR_INGREDIENT),
        jsonObject.getString(STR_DESCRIPTION),
    )
    constructor(cursor: Cursor) : this(
        cursor.getString(cursor.getColumnIndex(INGREDIENT_KEY_ID)),
        cursor.getString(cursor.getColumnIndex(INGREDIENT_KEY_NAME)),
        cursor.getString(cursor.getColumnIndex(INGREDIENT_KEY_DESCRIPTION))
    )

    fun getContentValue() = ContentValues().apply {
        put(INGREDIENT_KEY_ID, id)
        put(INGREDIENT_KEY_NAME, name)
        put(INGREDIENT_KEY_DESCRIPTION, description)
    }

    companion object {
        const val INGREDIENT_TABLE_NAME = "ingredient"
        const val INGREDIENT_KEY_ID = "id"
        const val INGREDIENT_KEY_NAME = "name"
        const val INGREDIENT_KEY_DESCRIPTION = "description"
    }
}
