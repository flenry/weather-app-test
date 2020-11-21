package com.example.weatherapp2.view

import com.example.weatherapp2.model.data_class.City
import com.example.weatherapp2.model.data_class.WeatherDataModel

interface MainActivityView {
    fun handleProgressBarVisibility(visibility: Int)
    fun onCityListFetchSuccess(cityList: MutableList<City>)
    fun onCityListFetchFailure(errorMessage: String)
    fun onWeatherInfoFetchSuccess(weatherDataModel: WeatherDataModel)
    fun onWeatherInfoFetchFailure(errorMessage: String)
}