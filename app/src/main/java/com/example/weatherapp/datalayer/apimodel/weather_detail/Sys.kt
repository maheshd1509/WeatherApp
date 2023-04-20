package com.example.weatherapp.datalayer.apimodel.weather_detail

data class Sys(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)