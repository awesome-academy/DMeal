package com.sunasterisk.dmealfoodapp.data.source.local.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.sunasterisk.dmealfoodapp.data.model.Ingredient.Companion.INGREDIENT_KEY_DESCRIPTION
import com.sunasterisk.dmealfoodapp.data.model.Ingredient.Companion.INGREDIENT_KEY_ID
import com.sunasterisk.dmealfoodapp.data.model.Ingredient.Companion.INGREDIENT_KEY_NAME
import com.sunasterisk.dmealfoodapp.data.model.Ingredient.Companion.INGREDIENT_TABLE_NAME
import com.sunasterisk.dmealfoodapp.data.model.Meal.Companion.MEAL_KEY_ID
import com.sunasterisk.dmealfoodapp.data.model.Meal.Companion.MEAL_KEY_IMAGE
import com.sunasterisk.dmealfoodapp.data.model.Meal.Companion.MEAL_KEY_NAME
import com.sunasterisk.dmealfoodapp.data.model.Meal.Companion.MEAL_TABLE_NAME

class AppDatabase private constructor(
    context: Context?,
    dbName: String,
    version: Int
) : SQLiteOpenHelper(context, dbName, null, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.apply {
            execSQL(CREATE_MEAL_TABLE)
            execSQL(CREATE_INGREDIENT_TABLE)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.apply {
            execSQL(DROP_MEAL_TABLE)
            execSQL(DROP_INGREDIENT_TABLE)
        }
        onCreate(db)
    }

    companion object {
        private const val DB_NAME = "dmeal.db"
        private const val DB_VERSION = 1

        private const val CREATE_MEAL_TABLE =
            "CREATE TABLE $MEAL_TABLE_NAME (" +
                    "$MEAL_KEY_ID INTEGER PRIMARY KEY, " +
                    "$MEAL_KEY_NAME TEXT, " +
                    "$MEAL_KEY_IMAGE TEXT)"

        private val DROP_MEAL_TABLE =
            String.format("DROP TABLE IF EXISTS %s", MEAL_TABLE_NAME)

        private const val CREATE_INGREDIENT_TABLE =
            "CREATE TABLE $INGREDIENT_TABLE_NAME (" +
                    "$INGREDIENT_KEY_ID INTEGER PRIMARY KEY, " +
                    "$INGREDIENT_KEY_NAME TEXT, " +
                    "$INGREDIENT_KEY_DESCRIPTION TEXT)"

        private val DROP_INGREDIENT_TABLE =
            String.format("DROP TABLE IF EXISTS %s", INGREDIENT_TABLE_NAME)

        private val lock = Any()
        private var instance: AppDatabase? = null

        fun getInstance(context: Context?) =
            instance ?: synchronized(lock) {
                instance ?: AppDatabase(context, DB_NAME, DB_VERSION).also { instance = it }
            }
    }
}
