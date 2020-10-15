package ru.suvorov.weather.core.port.secondary

import ru.suvorov.weather.core.component.weather.Weather

interface WeatherService {

    fun getWeatherByCity(city: String): Weather
}