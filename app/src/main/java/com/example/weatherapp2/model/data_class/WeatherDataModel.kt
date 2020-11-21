package com.example.weatherapp2.model.data_class

data class WeatherDataModel (
        var dateTime: String = "",
        var temperature: String = "0",
        var cityAndCountry: String = "",
        var humidity: String = "",
        var pressure: String = "",
        var visibility: String = "",
        var sunrise: String = "",
        var sunset: String = ""
)