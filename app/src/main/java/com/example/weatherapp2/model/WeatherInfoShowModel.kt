package com.example.weatherapp2.model

import com.example.weatherapp2.common.RequestCompleteListener
import com.example.weatherapp2.model.data_class.City
import com.example.weatherapp2.model.data_class.WeatherInfoResponse

interface WeatherInfoShowModel {
    fun getCityList(callback: RequestCompleteListener<MutableList<City>>)
    fun getWeatherInformation(cityId: Int, callback: RequestCompleteListener<WeatherInfoResponse>)
}