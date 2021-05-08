package com.sunasterisk.dmealfoodapp.ui.main

import android.content.Context
import android.content.Intent
import com.sunasterisk.dmealfoodapp.R
import com.sunasterisk.dmealfoodapp.base.BaseActivity

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun onCreatedView() {
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }
}
