package com.sunasterisk.dmealfoodapp.data.source.local.dao

import android.annotation.SuppressLint
import com.sunasterisk.dmealfoodapp.data.model.Ingredient
import com.sunasterisk.dmealfoodapp.data.model.Ingredient.Companion.INGREDIENT_KEY_ID
import com.sunasterisk.dmealfoodapp.data.model.Ingredient.Companion.INGREDIENT_TABLE_NAME
import com.sunasterisk.dmealfoodapp.data.model.Meal
import com.sunasterisk.dmealfoodapp.data.source.local.db.AppDatabase

class IngredientDaoImpl private constructor(database: AppDatabase) : IngredientDao {

    private val writableDB = database.writableDatabase
    private val readableDB = database.readableDatabase

    override fun insertIngredient(ingredient: Ingredient) =
        writableDB.insert(INGREDIENT_TABLE_NAME, null, ingredient.getContentValue())

    override fun deleteIngredient(idIngredient: String) =
        writableDB.delete(
            INGREDIENT_TABLE_NAME, "${Meal.MEAL_KEY_ID}=?", arrayOf(idIngredient)) > 0

    override fun getAllIngredients(): List<Ingredient> {
        val ingredients = mutableListOf<Ingredient>()
        val cursor = readableDB.query(
            INGREDIENT_TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
        cursor.use {
            while (it.moveToNext()) {
                ingredients.add(Ingredient(it))
            }
        }
        return ingredients
    }

    @SuppressLint("Recycle")
    override fun isFavorite(ingredientId: String): Int {
        val cursor = readableDB.query(
            INGREDIENT_TABLE_NAME,
            null,
            "$INGREDIENT_KEY_ID = ?",
            arrayOf(ingredientId),
            null,
            null,
            null
        )
        return cursor.count
    }

    companion object {
        private var instance: IngredientDaoImpl? = null

        fun getInstance(database: AppDatabase): IngredientDaoImpl =
            instance ?: IngredientDaoImpl(database).also {
                instance = it
            }
    }
}
