package com.sunasterisk.dmealfoodapp.data.source

import com.sunasterisk.dmealfoodapp.data.model.MealCategory
import com.sunasterisk.dmealfoodapp.data.source.ultils.OnDataCallback

interface MealCategoryDataSource {
    fun getMealCategories(callback: OnDataCallback<List<MealCategory>>)
}
