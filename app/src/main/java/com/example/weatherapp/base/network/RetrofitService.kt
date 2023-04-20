package com.example.weatherapp.base.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitService @Inject constructor() {

   companion object{
       private const val baseUrl = "https://api.openweathermap.org/geo/1.0/"
   }

    fun <T> buildAPI(api: Class<T>): T {
        return getService(api)
    }

    /*This class return api service class instance eg:RetrofitService etc*/
    private fun <T> getService(api: Class<T>): T {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(api)
    }

}