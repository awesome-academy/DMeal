package com.sunasterisk.dmealfoodapp.ui.splash

import android.os.Handler
import android.os.Looper
import com.sunasterisk.dmealfoodapp.R
import com.sunasterisk.dmealfoodapp.base.BaseActivity
import com.sunasterisk.dmealfoodapp.ui.main.MainActivity

private const val TIME_WAITING = 2000L

class SplashActivity : BaseActivity(R.layout.activity_splash) {
    override fun onCreatedView() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(MainActivity.getIntent(this))
            finish()
        }, TIME_WAITING)
    }
}
