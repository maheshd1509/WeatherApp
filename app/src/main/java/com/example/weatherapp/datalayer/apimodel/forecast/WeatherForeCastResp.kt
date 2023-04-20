package com.example.weatherapp.datalayer.apimodel.forecast

data class WeatherForeCastResp(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherForecast>,
    val message: Int
)