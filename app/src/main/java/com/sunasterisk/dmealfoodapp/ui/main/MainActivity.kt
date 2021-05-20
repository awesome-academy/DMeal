package com.sunasterisk.dmealfoodapp.ui.main

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sunasterisk.dmealfoodapp.R
import com.sunasterisk.dmealfoodapp.base.BaseActivity
import com.sunasterisk.dmealfoodapp.databinding.ActivityMainBinding
import com.sunasterisk.dmealfoodapp.ui.category.MealCategoryFragment
import com.sunasterisk.dmealfoodapp.ui.ingredient.IngredientListFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val onBottomNavigationView =
        BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.menuCategory -> openFragment(MealCategoryFragment())
                R.id.menuIngredient -> openFragment(IngredientListFragment())
            }
            true
        }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreatedView() {
        binding.bottomNavigationView.apply {
            setOnNavigationItemSelectedListener(onBottomNavigationView)
            selectedItemId = R.id.menuCategory
        }
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }

}
