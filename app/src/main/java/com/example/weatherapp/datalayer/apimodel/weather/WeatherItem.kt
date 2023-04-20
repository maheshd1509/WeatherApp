package com.example.weatherapp.datalayer.apimodel.weather

data class WeatherItem(
    val country: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val state: String
)