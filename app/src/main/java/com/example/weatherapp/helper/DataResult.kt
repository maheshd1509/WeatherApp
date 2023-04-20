package com.example.weatherapp.helper

sealed class DataResult<out R>{
    data class Success<T>(val data:T): DataResult<T>()
    data class Error(val exception: Exception?): DataResult<Nothing>()
    object Loading: DataResult<Nothing>()

}