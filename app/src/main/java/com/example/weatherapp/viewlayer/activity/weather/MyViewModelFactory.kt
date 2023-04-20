package com.example.weatherapp.viewlayer.activity.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.datalayer.repository.WeatherDataRepositoryImpl

class MyViewModelFactory(private val weatherDataRepositoryImpl: WeatherDataRepositoryImpl) : ViewModelProvider.Factory {

    /**
     * Create this method help to create viewmodel class instance with parameters
     *
     * @param T
     * @param modelClass
     * @return
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(weatherDataRepositoryImpl) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}