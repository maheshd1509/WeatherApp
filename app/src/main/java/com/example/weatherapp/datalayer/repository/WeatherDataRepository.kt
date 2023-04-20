package com.example.weatherapp.datalayer.repository

import com.example.weatherapp.datalayer.apimodel.forecast.WeatherForeCastResp
import com.example.weatherapp.datalayer.apimodel.weather.WeatherItem
import com.example.weatherapp.datalayer.apimodel.weather_detail.WeatherDetail
import com.example.weatherapp.helper.DataResult

interface WeatherDataRepository {
    suspend fun getWeatherByCityName(cityName: String): DataResult<List<WeatherItem>?>
    suspend fun getWeatherDetailByLatLong(lat: String, lon: String): DataResult<WeatherDetail?>
    suspend fun getWeatherForecastByLatLong(lat: String, lon: String): DataResult<WeatherForeCastResp?>

}