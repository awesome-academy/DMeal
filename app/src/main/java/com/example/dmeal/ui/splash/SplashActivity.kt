package com.example.dmeal.ui.splash

import android.os.Handler
import android.os.Looper
import com.example.dmeal.R
import com.example.dmeal.base.BaseActivity
import com.example.dmeal.ui.main.MainActivity

private const val TIME_WAITING = 2000L

class SplashActivity : BaseActivity(R.layout.activity_splash) {
    override fun onCreatedView() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(MainActivity.getIntent(this))
            finish()
        }, TIME_WAITING)
    }
}
