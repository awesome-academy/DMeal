package com.example.dmeal.ui.main

import android.content.Context
import android.content.Intent
import com.example.dmeal.R
import com.example.dmeal.base.BaseActivity

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun onCreatedView() {
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }
}
