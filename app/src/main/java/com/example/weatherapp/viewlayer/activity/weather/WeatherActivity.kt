package com.example.weatherapp.viewlayer.activity.weather

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.base.network.RetrofitService
import com.example.weatherapp.base.network.WeatherApiService
import com.example.weatherapp.datalayer.repository.WeatherDataRepositoryImpl
import com.example.weatherapp.helper.Constant
import com.example.weatherapp.helper.DataResult
import com.example.weatherapp.helper.PrefHelper

class WeatherActivity : AppCompatActivity() {
//    @Inject
    lateinit var weatherViewModel: WeatherViewModel
    private lateinit var progressBarssBar: ProgressBar
    private lateinit var dataNotAvailableTxt: TextView
    private lateinit var cityEdit: EditText
    private lateinit var citySearchBtn: Button
    private lateinit var prefHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_activity)
        getPermissionApproval()
        initViews()
    }

    /**
     * Initviews
     * this method help to initials the views and other requested things
     */
    private fun initViews() {
        progressBarssBar = findViewById(R.id.progressBar)
        dataNotAvailableTxt = findViewById(R.id.dataNotAvailableTxt)
        cityEdit = findViewById(R.id.cityEdit)
        citySearchBtn = findViewById(R.id.citySearchBtn)
        prefHelper = PrefHelper(this)
//        val activityComponent = DaggerActivityComponent.builder()
//            .myAppComponent((application as MyApplication).appComponent)
//            .activityModule(ActivityModule(this))
//            .build()
//        activityComponent.inject(this)
        val apiService = RetrofitService().buildAPI(WeatherApiService::class.java)
        val weatherDataRepositoryImpl = WeatherDataRepositoryImpl(apiService)
        weatherViewModel = ViewModelProvider(
            this,
            MyViewModelFactory(weatherDataRepositoryImpl)
        )[WeatherViewModel::class.java]


        val cityName = prefHelper.getString(Constant.PREF_CITYNAME)
        if (cityName?.isNotEmpty() == true)
            fetchAndObserveApiData(cityEdit.text.toString())

        citySearchBtn.setOnClickListener {
            if (cityEdit.text.toString().isNotEmpty())
                fetchAndObserveApiData(cityEdit.text.toString())
            else
                Toast.makeText(
                    this@WeatherActivity,
                    "Please enter city/county/state",
                    Toast.LENGTH_SHORT
                ).show()

        }


    }

    /**
     * Get permission approval
     * this method request for permission
     */
    private fun getPermissionApproval() {
        if (ActivityCompat.checkSelfPermission(
                this@WeatherActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this@WeatherActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                ActivityCompat.requestPermissions(
                    this@WeatherActivity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    1
                )
            } else {
                ActivityCompat.requestPermissions(
                    this@WeatherActivity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    1
                )
            }
        }
    }

    /**
     * this method help to Fetch and observe weather api data
     *
     * @param cityName
     */
    private fun fetchAndObserveApiData(cityName: String) {

        weatherViewModel.getWeatherInfoByCityName(cityName = cityName)
        weatherViewModel.weatherData.observe(this@WeatherActivity) { it ->
            when (it) {
                is DataResult.Loading -> {
                    progressBarssBar.visibility = View.VISIBLE
                }
                is DataResult.Success -> {
                    progressBarssBar.visibility = View.GONE
                    val weatherDataList = it.data
                    prefHelper.put(Constant.PREF_CITYNAME, cityName)
                    findViewById<RecyclerView>(R.id.recyclerview).apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@WeatherActivity)
                        adapter = WeatherAdapter(weatherDataList,this@WeatherActivity)
                    }
                }
                is DataResult.Error -> {
                    progressBarssBar.visibility = View.GONE
                    dataNotAvailableTxt.visibility = View.VISIBLE
                }
            }

        }

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(
                            this@WeatherActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }
}