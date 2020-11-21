package com.example.weatherapp2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.weatherapp2.R
import com.example.weatherapp2.model.WeatherInfoShowModel
import com.example.weatherapp2.model.WeatherInfoShowModelImpl
import com.example.weatherapp2.model.data_class.City
import com.example.weatherapp2.model.data_class.WeatherDataModel
import com.example.weatherapp2.presenter.WeatherInfoShowPresenter
import com.example.weatherapp2.presenter.WeatherInfoShowPresenterImpl
import com.example.weatherapp2.utils.convertToListOfCityName
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_weather_elements.*


class MainActivity : AppCompatActivity(), MainActivityView {

    private lateinit var model: WeatherInfoShowModel
    private lateinit var presenter: WeatherInfoShowPresenter

    private var cityList: MutableList<City> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = WeatherInfoShowModelImpl(applicationContext)
        presenter = WeatherInfoShowPresenterImpl(this, model)

        // call for fetching city list
        presenter.fetchCityList()

        button_get_weather.setOnClickListener {
            layout_weather_info.visibility = View.GONE

            val spinnerSelectedItemPos = spinner.selectedItemPosition

            // fetch weather info of specific city
            presenter.fetchWeatherInfo(cityList[spinnerSelectedItemPos].id)
        }
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun handleProgressBarVisibility(visibility: Int) {
        progressBar?.visibility = visibility
    }

    /**
     * This method will be triggered when city list successfully fetched.
     */
    override fun onCityListFetchSuccess(cityList: MutableList<City>) {
        this.cityList = cityList

        val arrayAdapter = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            cityList.convertToListOfCityName()
        )

        spinner.adapter = arrayAdapter
    }

    /**
     * This method will triggered if city list fetching process failed
     */
    override fun onCityListFetchFailure(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    /**
     * This method will triggered when weather information successfully fetched.
     */
    override fun onWeatherInfoFetchSuccess(weatherDataModel: WeatherDataModel) {
        layout_weather_info.visibility = View.VISIBLE

        tv_temperature_value?.text = weatherDataModel.temperature
        tv_humidity_value?.text = weatherDataModel.humidity
        tv_pressure_value?.text = weatherDataModel.pressure
        tv_visibility_value?.text = weatherDataModel.visibility


        tv_sunrise_value?.text = weatherDataModel.sunrise
        tv_sunset_value?.text = weatherDataModel.sunset
    }

    /**
     * This method will triggered if weather information fetching process failed
     */
    override fun onWeatherInfoFetchFailure(errorMessage: String) {
        layout_weather_info.visibility = View.GONE
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()

    }

}