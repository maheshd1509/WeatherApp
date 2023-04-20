package com.example.weatherapp.viewlayer.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import com.example.weatherapp.viewlayer.activity.weather.WeatherActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000) // 1000 is the delayed time in milliseconds.
    }
}