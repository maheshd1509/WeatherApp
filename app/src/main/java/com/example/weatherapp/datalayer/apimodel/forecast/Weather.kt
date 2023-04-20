package com.example.weatherapp.datalayer.apimodel.forecast

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)