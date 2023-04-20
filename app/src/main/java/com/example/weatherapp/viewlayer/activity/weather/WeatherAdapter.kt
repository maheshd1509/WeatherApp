package com.example.weatherapp.viewlayer.activity.weather

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.datalayer.apimodel.weather.WeatherItem
import com.example.weatherapp.helper.Constant
import com.example.weatherapp.viewlayer.activity.weatherdetail.WeatherDetailActivity

class WeatherAdapter(private val list: List<WeatherItem>?, private val context: WeatherActivity) :
    RecyclerView.Adapter<WeatherAdapter.WeatherHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_item, parent, false)

        return WeatherHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        val weatherItem = list?.get(position)
        holder.cityName.text = weatherItem?.name ?: ""
        holder.countryName.text = weatherItem?.country ?: ""
        holder.stateName.text = weatherItem?.state ?: ""
        holder.coordinates.text = "(${weatherItem?.lat},${weatherItem?.lon})"
        holder.container.setOnClickListener {
            val intent = Intent(context, WeatherDetailActivity::class.java)
            val bundle = Bundle()
            bundle.putString(Constant.PREF_LAT, weatherItem?.lat.toString())
            bundle.putString(Constant.PREF_LNG, weatherItem?.lon.toString())
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    class WeatherHolder(item: View) : RecyclerView.ViewHolder(item) {
        var cityName: TextView = item.findViewById(R.id.cityName)
        var stateName: TextView = item.findViewById(R.id.stateName)
        var countryName: TextView = item.findViewById(R.id.countryName)
        var coordinates: TextView = item.findViewById(R.id.coordinates)
        val container: ConstraintLayout = item.findViewById(R.id.ll_parent)
    }

}