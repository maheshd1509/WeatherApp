package com.example.weatherapp.datalayer.repository

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.base.network.WeatherApiService
import com.example.weatherapp.datalayer.apimodel.forecast.WeatherForeCastResp
import com.example.weatherapp.datalayer.apimodel.weather.WeatherItem
import com.example.weatherapp.datalayer.apimodel.weather_detail.WeatherDetail
import com.example.weatherapp.helper.DataResult
import javax.inject.Inject

class WeatherDataRepositoryImpl @Inject constructor(private val retrofitService: WeatherApiService) :
    WeatherDataRepository {

    override suspend fun getWeatherByCityName(cityName: String): DataResult<List<WeatherItem>?> {
        return try {
            val call = retrofitService.getWeatherByCityName(city = cityName)
            val data = call.body()
            DataResult.Success(data)
        } catch (e: Exception) {
            DataResult.Error(e)
        }
    }

    override suspend fun getWeatherDetailByLatLong(
        lat: String,
        lon: String
    ): DataResult<WeatherDetail?> {
        return try {
            val url =
                "https://api.openweathermap.org/data/2.5/weather?lat=$lat&lon=$lon&appid=${BuildConfig.ApiKey}"
            val call = retrofitService.getWeatherDetailByLatLong(url = url)
            val data: WeatherDetail? = call.body()
            DataResult.Success(data)
        } catch (e: Exception) {
            DataResult.Error(e)
        }
    }

    override suspend fun getWeatherForecastByLatLong(
        lat: String,
        lon: String
    ): DataResult<WeatherForeCastResp?> {
        return try {
            val url =
                "https://api.openweathermap.org/data/2.5/forecast?lat=" + lat + "&lon=" + lon + "&appid=" + BuildConfig.ApiKey + "&units=metric"
            val call = retrofitService.getWeatherForeCastByLatLong(url = url)
            val data: WeatherForeCastResp? = call.body()
            DataResult.Success(data)
        } catch (e: Exception) {
            DataResult.Error(e)
        }
    }
}