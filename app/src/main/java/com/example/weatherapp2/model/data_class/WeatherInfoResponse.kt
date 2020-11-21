package com.example.weatherapp2.model.data_class

import android.media.MicrophoneInfo
import com.google.gson.annotations.SerializedName

data class WeatherInfoResponse (

        @SerializedName("base")
        val base: String = "",
        @SerializedName("main")
        val main: Main = Main(),
        @SerializedName("weather")
        val weather: List<Weather> = listOf(),
        @SerializedName("visibility")
        val visibility: Int = 0,
        @SerializedName("dt")
        val dt: Int = 0,
        @SerializedName("sys")
        val sys: Sys = Sys(),
        @SerializedName("id")
        val id: Int = 0,
        @SerializedName("name")
        val name: String = "",
        @SerializedName("cod")
        val cod: Int = 0
)