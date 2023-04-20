package com.example.weatherapp.viewlayer.activity.weatherdetail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.base.network.RetrofitService
import com.example.weatherapp.base.network.WeatherApiService
import com.example.weatherapp.datalayer.apimodel.forecast.WeatherForeCastResp
import com.example.weatherapp.datalayer.apimodel.forecast.WeatherForecast
import com.example.weatherapp.datalayer.repository.WeatherDataRepositoryImpl
import com.example.weatherapp.helper.Constant
import com.example.weatherapp.helper.DataResult
import com.example.weatherapp.helper.MyUtility
import com.example.weatherapp.viewlayer.activity.weather.MyViewModelFactory
import com.example.weatherapp.viewlayer.activity.weather.WeatherViewModel
import java.util.*

class WeatherForecastActivity : AppCompatActivity() {
    private lateinit var city: TextView
    private lateinit var temp: TextView
    private lateinit var main: TextView
    private lateinit var cityTime: TextView
    private lateinit var humidity: TextView
    private lateinit var wind: TextView
    private lateinit var realFeel: TextView
    private lateinit var todayDayAndTime: TextView
    private lateinit var weatherImage: ImageView
    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initViewModel()
    }

    private fun initViews() {
        city = findViewById(R.id.id_city)
        temp = findViewById(R.id.id_degree)
        main = findViewById(R.id.id_main)
        humidity = findViewById(R.id.id_humidity)
        wind = findViewById(R.id.id_wind)
        realFeel = findViewById(R.id.id_realfeel)
        weatherImage = findViewById(R.id.id_weatherImage)
        todayDayAndTime = findViewById(R.id.id_time)
    }

    private fun initViewModel() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val apiService = RetrofitService().buildAPI(WeatherApiService::class.java)
        val weatherDataRepositoryImpl = WeatherDataRepositoryImpl(apiService)
        weatherViewModel = ViewModelProvider(
            this,
            MyViewModelFactory(weatherDataRepositoryImpl)
        )[WeatherViewModel::class.java]
        val bundle = intent.extras
        if (bundle != null) {
            val lat: String = bundle.getString(Constant.PREF_LAT).toString()
            val lon: String = bundle.getString(Constant.PREF_LNG).toString()
            if (lat.isNotEmpty() && lon.isNotEmpty())
                fetchWeatherByLatLon(lat, lon)
        }

    }

    private fun setValueToView(weatherDetail: WeatherForeCastResp) {
        city.text = weatherDetail.city.name
        val weatherForecast = weatherDetail.list[0]
        temp.text = weatherForecast.main.temp.toString() + "°"
        todayDayAndTime.text = weatherForecast.dt_txt
        humidity.text = String.format(
            resources.getString(R.string.humidity),
            weatherForecast.main.humidity.toString()
        )
        wind.text =
            String.format(resources.getString(R.string.wind), weatherForecast.wind.deg.toString())
        realFeel.text = String.format(
            resources.getString(R.string.real_feel),
            weatherForecast.main.feels_like.toString()
        )
        main.text = weatherForecast.weather[0].description
        MyUtility.setDataImage(weatherImage, weatherForecast.weather[0].icon, resources)

    }


    private fun fetchWeatherByLatLon(lat: String, lon: String) {
        weatherViewModel.getWeatherForecastByCityLatLong(lat, lon)
        weatherViewModel.weatherForeCastData.observe(this@WeatherForecastActivity) { it ->
            when (it) {
                is DataResult.Success -> {
                    val weatherDetailData = it.data
                    weatherDetailData?.let { it1 -> setValueToView(it1) }
                    val forecast = arrayOfNulls<TextView>(5)
                    val forecastTemp = arrayOfNulls<TextView>(5)
                    val forecastIcons = arrayOfNulls<ImageView>(5)
                    idAssign(forecast, forecastTemp, forecastIcons)
                    for (i in forecast.indices) {
                        forecastCal(
                            forecast[i],
                            forecastTemp[i],
                            forecastIcons[i],
                            i,
                            weatherDetailData
                        )
                    }
                }
                else -> {
                    //
                }
            }

        }
    }

    private fun idAssign(
        forecast: Array<TextView?>,
        forecastTemp: Array<TextView?>,
        forecastIcons: Array<ImageView?>
    ) {
        forecast[0] = findViewById(R.id.id_forecastDay1)
        forecast[1] = findViewById(R.id.id_forecastDay2)
        forecast[2] = findViewById(R.id.id_forecastDay3)
        forecast[3] = findViewById(R.id.id_forecastDay4)
        forecast[4] = findViewById(R.id.id_forecastDay5)
        forecastTemp[0] = findViewById(R.id.id_forecastTemp1)
        forecastTemp[1] = findViewById(R.id.id_forecastTemp2)
        forecastTemp[2] = findViewById(R.id.id_forecastTemp3)
        forecastTemp[3] = findViewById(R.id.id_forecastTemp4)
        forecastTemp[4] = findViewById(R.id.id_forecastTemp5)
        forecastIcons[0] = findViewById(R.id.id_forecastIcon1)
        forecastIcons[1] = findViewById(R.id.id_forecastIcon2)
        forecastIcons[2] = findViewById(R.id.id_forecastIcon3)
        forecastIcons[3] = findViewById(R.id.id_forecastIcon4)
        forecastIcons[4] = findViewById(R.id.id_forecastIcon5)
    }

    private fun forecastCal(
        forecast: TextView?,
        forecastTemp: TextView?,
        forecastIcons: ImageView?,
        index: Int,
        weatherForeCastResp: WeatherForeCastResp?
    ) {
        val list = weatherForeCastResp?.list
        if (index>= list!!.size) return
        val weatherForecast = list.get(index)
        val dt = weatherForecast.dt_txt // dt_text.format=2020-06-26 12:00:00
        val a = dt.split(" ").toTypedArray()
        a?.let { setDataToView(it, forecast, forecastTemp, forecastIcons, weatherForecast) }

    }

    private fun setDataToView(
        a: Array<String>, forecast: TextView?,
        forecastTemp: TextView?,
        forecastIcons: ImageView?,
        weatherForecast: WeatherForecast
    ) {
        val dateSplit = a[0].split("-").toTypedArray()
        val calendar: Calendar = GregorianCalendar(
            dateSplit[0].toInt(),
            dateSplit[1].toInt() - 1,
            dateSplit[2].toInt()
        )
        val forecastDate = calendar.time
        val dateString = forecastDate.toString()
        val forecastDateSplit = dateString.split(" ").toTypedArray()
        val date =
            forecastDateSplit[0] + ", " + forecastDateSplit[1] + " " + forecastDateSplit[2]
        setDataText(forecast, date)
        val temparature = weatherForecast.main.temp
        val temp = Math.round(temparature).toString() + "°"
        setDataText(forecastTemp, temp)
        val array = weatherForecast.weather
        val object1 = array[0]
        val icons = object1.icon
        MyUtility.setDataImage(forecastIcons, icons, resources)
    }

    private fun setDataText(text: TextView?, value: String) {
        runOnUiThread { text!!.text = value }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}