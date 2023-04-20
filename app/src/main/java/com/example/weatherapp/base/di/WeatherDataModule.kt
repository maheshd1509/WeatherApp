package com.example.weatherapp.base.di

import com.example.weatherapp.datalayer.repository.WeatherDataRepository
import com.example.weatherapp.datalayer.repository.WeatherDataRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class WeatherDataModule {
    @Binds
    abstract fun provideWeatherDataModuleViewModel(weatherDataRepositoryImpl: WeatherDataRepositoryImpl): WeatherDataRepository

}