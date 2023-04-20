package com.example.weatherapp.viewlayer.activity.weatherdetail

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.base.network.RetrofitService
import com.example.weatherapp.base.network.WeatherApiService
import com.example.weatherapp.datalayer.apimodel.weather_detail.WeatherDetail
import com.example.weatherapp.datalayer.repository.WeatherDataRepositoryImpl
import com.example.weatherapp.helper.Constant
import com.example.weatherapp.helper.DataResult
import com.example.weatherapp.helper.MyUtility
import com.example.weatherapp.viewlayer.activity.weather.MyViewModelFactory
import com.example.weatherapp.viewlayer.activity.weather.WeatherViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class WeatherDetailActivity : AppCompatActivity() {
    private lateinit var city: TextView
    private lateinit var temp: TextView
    private lateinit var main: TextView
    private lateinit var humidity: TextView
    private lateinit var wind: TextView
    private lateinit var realFeel: TextView
    private lateinit var todayDayAndTime: TextView
    private lateinit var weatherImage: ImageView
    lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_detail)
        initViews()
        initViewModel()
    }

    private fun initViews() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        city = findViewById(R.id.id_city)
        temp = findViewById(R.id.id_degree)
        main = findViewById(R.id.id_main)
        humidity = findViewById(R.id.id_humidity)
        wind = findViewById(R.id.id_wind)
        realFeel = findViewById(R.id.id_realfeel)
        weatherImage = findViewById(R.id.id_weatherImage)
        todayDayAndTime = findViewById(R.id.id_time)

    }

    /**
     * Initials view model
     *
     */
    private fun initViewModel() {
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
            if (lat.isNotEmpty() && lon.isNotEmpty()) {
                fetchWeatherByLatLon(lat, lon)
                findViewById<Button>(R.id.checkforecastBtn).setOnClickListener {
                    val intent = Intent(this, WeatherForecastActivity::class.java)
                    val bundle = Bundle()
                    bundle.putString(Constant.PREF_LAT, lat)
                    bundle.putString(Constant.PREF_LNG, lon)
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
            }

        }

    }

    /**
     * Fetch weather by lat lon
     *
     * @param lat
     * @param lon
     */
    private fun fetchWeatherByLatLon(lat: String, lon: String) {
        weatherViewModel.getWeatherDetailByCityLatLong(lat, lon)
        weatherViewModel.weatherDetailData.observe(this@WeatherDetailActivity) { it ->
            when (it) {
                is DataResult.Success -> {
                    val weatherDetailData = it.data
                    weatherDetailData?.let { it1 -> setValueToView(it1) }
                }
                else -> {
                    //
                }
            }

        }
    }

    /**
     * Set value to view
     *
     * @param weatherDetail
     */
    private fun setValueToView(weatherDetail: WeatherDetail) {
        city.text = weatherDetail.name
        temp.text = weatherDetail.main.temp.toString() + "°"
        val formatter: DateFormat = SimpleDateFormat("E, dd MMM yyyy HH:mm:ss")
        todayDayAndTime.text = formatter.format(Date(weatherDetail.dt * 1000L))
        humidity.text = String.format(
            resources.getString(R.string.humidity),
            weatherDetail.main.humidity.toString()
        )
        wind.text =
            String.format(resources.getString(R.string.wind), weatherDetail.wind.deg.toString())
        realFeel.text = String.format(
            resources.getString(R.string.real_feel),
            weatherDetail.main.feels_like.toString()
        )
        main.text = weatherDetail.weather[0].description
        MyUtility.setDataImage(weatherImage, weatherDetail.weather[0].icon, resources)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}