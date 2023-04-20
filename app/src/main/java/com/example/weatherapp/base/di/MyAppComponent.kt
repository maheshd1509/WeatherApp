package com.example.weatherapp.base.di

import com.example.weatherapp.datalayer.repository.WeatherDataRepository
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [MyAppModule::class, WeatherDataModule::class, RetrofitModule::class])
interface MyAppComponent {
    val weatherDataRepository:WeatherDataRepository
}

