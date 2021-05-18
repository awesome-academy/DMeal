package com.sunasterisk.dmealfoodapp.ui.detailmeal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.sunasterisk.dmealfoodapp.data.model.Meal
import com.sunasterisk.dmealfoodapp.databinding.ActivityMealDetailBinding

class MealDetailActivity : YouTubeBaseActivity() {

    private lateinit var binding: ActivityMealDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        const val MEAL = "MEAL"

        fun getInstance(context: Context?, meal: Meal) =
            Intent(context, MealDetailActivity::class.java).apply {
                putExtra(MEAL, meal)
            }
    }
}
