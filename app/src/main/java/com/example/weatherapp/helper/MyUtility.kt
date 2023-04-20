package com.example.weatherapp.helper

import android.content.res.Resources
import android.widget.ImageView
import com.example.weatherapp.R

object MyUtility {
    fun setDataImage(ImageView: ImageView?, value: String, resources: Resources) {
        when (value) {
            "01d" -> ImageView?.setImageDrawable(resources.getDrawable(R.drawable.w01d))
            "01n" -> ImageView?.setImageDrawable(resources.getDrawable(R.drawable.w01d))
            "02d" -> ImageView?.setImageDrawable(resources.getDrawable(R.drawable.w02d))
            "02n" -> ImageView?.setImageDrawable(resources.getDrawable(R.drawable.w02d))
            "03d" -> ImageView?.setImageDrawable(resources.getDrawable(R.drawable.w03d))
            "03n" -> ImageView?.setImageDrawable(resources.getDrawable(R.drawable.w03d))
            "04d" -> ImageView?.setImageDrawable(resources.getDrawable(R.drawable.w04d))
            "04n" -> ImageView?.setImageDrawable(resources.getDrawable(R.drawable.w04d))
            "09d" -> ImageView?.setImageDrawable(resources.getDrawable(R.drawable.w09d))
            "09n" -> ImageView?.setImageDrawable(resources.getDrawable(R.drawable.w09d))
            "10d" -> ImageView?.setImageDrawable(resources.getDrawable(R.drawable.w10d))
            "10n" -> ImageView?.setImageDrawable(resources.getDrawable(R.drawable.w10d))
            "11d" -> ImageView?.setImageDrawable(resources.getDrawable(R.drawable.w11d))
            "11n" -> ImageView?.setImageDrawable(resources.getDrawable(R.drawable.w11d))
            "13d" -> ImageView?.setImageDrawable(resources.getDrawable(R.drawable.w13d))
            "13n" -> ImageView?.setImageDrawable(resources.getDrawable(R.drawable.w13d))
            else -> ImageView?.setImageDrawable(resources.getDrawable(R.drawable.w03d))
        }

    }

}