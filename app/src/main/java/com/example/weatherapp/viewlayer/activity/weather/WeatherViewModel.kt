package com.example.weatherapp.viewlayer.activity.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.datalayer.apimodel.forecast.WeatherForeCastResp
import com.example.weatherapp.datalayer.apimodel.weather.WeatherItem
import com.example.weatherapp.datalayer.apimodel.weather_detail.WeatherDetail
import com.example.weatherapp.datalayer.repository.WeatherDataRepositoryImpl
import com.example.weatherapp.helper.DataResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModel @Inject constructor(private val weatherDataRepositoryImpl: WeatherDataRepositoryImpl) :
    ViewModel() {
    private var _weatherDetailData = MutableLiveData<DataResult<WeatherDetail?>>()
    val weatherDetailData: LiveData<DataResult<WeatherDetail?>>
        get() = _weatherDetailData
    private var _weatherForeCastData = MutableLiveData<DataResult<WeatherForeCastResp?>>()
    val weatherForeCastData: LiveData<DataResult<WeatherForeCastResp?>>
        get() = _weatherForeCastData
    private var _weatherData = MutableLiveData<DataResult<List<WeatherItem>?>>()
    val weatherData: LiveData<DataResult<List<WeatherItem>?>>
        get() = _weatherData

    /**
     * Get weather info by city name
     *
     * @param cityName
     */
    fun getWeatherInfoByCityName(cityName: String) {
        viewModelScope.launch {
            try {
                _weatherData.value = DataResult.Loading
                val weatherData = weatherDataRepositoryImpl.getWeatherByCityName(cityName)
                _weatherData.value = weatherData
            } catch (e: Exception) {
                _weatherData.value = DataResult.Error(e)

            }
        }
    }

    /**
     * Get weather detail by city lat long
     *
     * @param lat
     * @param lon
     */
    fun getWeatherDetailByCityLatLong(
        lat: String,
        lon: String
    ) {
        viewModelScope.launch {
            try {
                _weatherDetailData.value = DataResult.Loading
                val weatherDetailData =
                    weatherDataRepositoryImpl.getWeatherDetailByLatLong(lat, lon)
                _weatherDetailData.value = weatherDetailData
            } catch (e: Exception) {
                _weatherDetailData.value = DataResult.Error(e)

            }
        }
    }

    /**
     * Get weather forecast data by city lat long
     *
     * @param lat
     * @param lon
     */
    fun getWeatherForecastByCityLatLong(
        lat: String,
        lon: String
    ) {
        viewModelScope.launch {
            try {
                _weatherForeCastData.value = DataResult.Loading
                val weatherForecastData =
                    weatherDataRepositoryImpl.getWeatherForecastByLatLong(lat, lon)
                _weatherForeCastData.value = weatherForecastData
            } catch (e: Exception) {
                _weatherForeCastData.value = DataResult.Error(e)

            }
        }
    }

}