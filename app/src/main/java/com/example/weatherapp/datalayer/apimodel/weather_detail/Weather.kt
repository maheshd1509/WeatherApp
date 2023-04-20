package com.example.weatherapp.datalayer.apimodel.weather_detail

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)