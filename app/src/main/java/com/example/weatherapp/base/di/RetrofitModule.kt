package com.example.weatherapp.base.di

import com.example.weatherapp.base.network.RetrofitService
import com.example.weatherapp.base.network.WeatherApiService
import dagger.Module
import dagger.Provides

@Module
class RetrofitModule {

    @Provides
    fun provideRetrofitService(retrofitBuilder: RetrofitService): WeatherApiService =
        retrofitBuilder.buildAPI(WeatherApiService::class.java)
}