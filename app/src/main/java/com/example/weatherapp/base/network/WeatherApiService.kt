package com.example.weatherapp.base.network

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.datalayer.apimodel.forecast.WeatherForeCastResp
import com.example.weatherapp.datalayer.apimodel.weather.WeatherItem
import com.example.weatherapp.datalayer.apimodel.weather_detail.WeatherDetail
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface WeatherApiService {

    @GET
    suspend fun getWeatherDetailByLatLong(@Url url: String): Response<WeatherDetail>

    @GET
    suspend fun getWeatherForeCastByLatLong(@Url url: String): Response<WeatherForeCastResp>

    @GET("direct?")
    suspend fun getWeatherByCityName(
        @Query("q") city: String,
        @Query("limit") limit: Int = 10,
        @Query("appid") appid: String = BuildConfig.ApiKey,
    ): Response<List<WeatherItem>>

    @GET("weather?")
    suspend fun getWeatherDetailByLatLong(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String = BuildConfig.ApiKey,
    ): Response<WeatherDetail>

    @GET("forecast?")
    suspend fun getForeCastWeatherDetailByLatLong(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String = BuildConfig.ApiKey,
        @Query("units") units: String = "metric"
    ): Response<okhttp3.Response>

}