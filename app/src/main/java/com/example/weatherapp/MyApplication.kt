package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.base.di.MyAppComponent
import com.example.weatherapp.base.di.MyAppModule
import com.example.weatherapp.base.di.DaggerMyAppComponent

class MyApplication : Application() {

    lateinit var appComponent: MyAppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = initDagger(this)
    }

    private fun initDagger(app: MyApplication): MyAppComponent =
        DaggerMyAppComponent.builder()
            .myAppModule(MyAppModule(app))
            .build()
}