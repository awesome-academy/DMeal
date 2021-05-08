package com.sunasterisk.dmealfoodapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sunasterisk.dmealfoodapp.R

abstract class BaseActivity(private val layout: Int) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        onCreatedView()
    }

    protected abstract fun onCreatedView()

    protected fun openFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
}
