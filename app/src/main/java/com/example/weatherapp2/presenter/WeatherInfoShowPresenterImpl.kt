package com.example.weatherapp2.presenter

import android.view.View
import com.example.weatherapp2.common.RequestCompleteListener
import com.example.weatherapp2.model.WeatherInfoShowModel
import com.example.weatherapp2.model.data_class.City
import com.example.weatherapp2.model.data_class.WeatherDataModel
import com.example.weatherapp2.model.data_class.WeatherInfoResponse
import com.example.weatherapp2.utils.kelvinToCelsius
import com.example.weatherapp2.utils.unixTimestampToDateTimeString
import com.example.weatherapp2.utils.unixTimestampToTimeString
import com.example.weatherapp2.view.MainActivityView

class WeatherInfoShowPresenterImpl(
        private var view: MainActivityView?,
        private val model: WeatherInfoShowModel) : WeatherInfoShowPresenter {

    override fun fetchCityList() {
        model.getCityList(object : RequestCompleteListener<MutableList<City>> {

            // if model successfully fetch the data from 'somewhere', this method will be called
            override fun onRequestSuccess(data: MutableList<City>) {
                view?.onCityListFetchSuccess(data)
            }

            // if model failed to fetch data then this method will be called
            override fun onRequestFailed(errorMessage: String) {
                view?.onCityListFetchFailure(errorMessage)
            }
        })
    }

    override fun fetchWeatherInfo(cityId: Int) {

        view?.handleProgressBarVisibility(View.VISIBLE) // let view know about progress bar visibility

        // call model's method for weather information
        model.getWeatherInformation(cityId, object : RequestCompleteListener<WeatherInfoResponse> {

            // if model successfully fetch the data from 'somewhere', this method will be called
            override fun onRequestSuccess(data: WeatherInfoResponse) {

                view?.handleProgressBarVisibility(View.GONE) // let view know about progress bar visibility

                // data formatting to show on UI
                val weatherDataModel = WeatherDataModel(
                    dateTime = data.dt.unixTimestampToDateTimeString(),
                    temperature = data.main.temp.kelvinToCelsius().toString(),
                    humidity = "${data.main.humidity}%",
                    pressure = "${data.main.pressure} mBar",
                    visibility = "${data.visibility/1000.0} KM",
                    sunrise = data.sys.sunrise.unixTimestampToTimeString(),
                    sunset = data.sys.sunset.unixTimestampToTimeString()
                )

                view?.onWeatherInfoFetchSuccess(weatherDataModel) //let view know the formatted weather data
            }

            // if model failed to fetch data then this method will be called
            override fun onRequestFailed(errorMessage: String) {
                view?.handleProgressBarVisibility(View.GONE) // let view know about progress bar visibility

                view?.onWeatherInfoFetchFailure(errorMessage) //let view know about failure
            }
        })
    }

    override fun detachView() {
        view = null
    }
}