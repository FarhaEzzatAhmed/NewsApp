package com.example.news_app.route.ui.Splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.news_app.R
import com.example.news_app.route.ui.main.MainActivity

class SplashActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        Handler(Looper.getMainLooper()).postDelayed({

            val intent = Intent(this@SplashActivity,MainActivity::class.java)
            startActivity(intent)
            finish()

        }, 1000)
    }
}