package com.sunasterisk.dmealfoodapp.data.repository

import com.sunasterisk.dmealfoodapp.data.model.MealCategory
import com.sunasterisk.dmealfoodapp.data.source.MealCategoryDataSource
import com.sunasterisk.dmealfoodapp.data.source.ultils.OnDataCallback

class MealCategoryRepository private constructor(
    private val remote: MealCategoryDataSource
) : MealCategoryDataSource {

    override fun getMealCategories(callback: OnDataCallback<List<MealCategory>>) {
        remote.getMealCategories(callback)
    }

    companion object {

        private var instance: MealCategoryRepository? = null
        fun getInstance(remote: MealCategoryDataSource) =
            instance ?: MealCategoryRepository(remote).also { instance = it }
    }
}
