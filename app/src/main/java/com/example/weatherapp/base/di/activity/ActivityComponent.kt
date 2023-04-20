package com.example.weatherapp.base.di.activity

import com.example.weatherapp.viewlayer.activity.weather.WeatherActivity
import com.example.weatherapp.viewlayer.activity.weatherdetail.WeatherDetailActivity
import com.example.weatherapp.base.di.MyAppComponent
import com.example.weatherapp.viewlayer.activity.weatherdetail.WeatherForecastActivity
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [MyAppComponent::class])
interface ActivityComponent {
    fun inject(weatherActivity: WeatherActivity)
    fun inject(weatherDetailActivity: WeatherDetailActivity)
    fun inject(weatherForecastActivity: WeatherForecastActivity)
}

