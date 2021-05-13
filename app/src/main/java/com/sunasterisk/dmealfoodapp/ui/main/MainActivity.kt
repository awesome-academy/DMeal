package com.sunasterisk.dmealfoodapp.ui.main

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sunasterisk.dmealfoodapp.R
import com.sunasterisk.dmealfoodapp.base.BaseActivity
import com.sunasterisk.dmealfoodapp.ui.category.MealCategoryFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(R.layout.activity_main) {


    private val onBottomNavigationView =
        BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.menuCategory -> openFragment(MealCategoryFragment())
            }
            true
        }

    override fun onCreatedView() {
        bottomNavigationView.apply {
            setOnNavigationItemSelectedListener(onBottomNavigationView)
            selectedItemId = R.id.menuCategory
        }
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }
}
