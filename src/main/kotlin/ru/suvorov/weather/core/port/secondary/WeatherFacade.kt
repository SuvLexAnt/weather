package ru.suvorov.weather.core.port.secondary

import ru.suvorov.weather.core.component.weather.Weather

interface WeatherFacade {

    fun getWeatherByCity(city: String): Weather
}