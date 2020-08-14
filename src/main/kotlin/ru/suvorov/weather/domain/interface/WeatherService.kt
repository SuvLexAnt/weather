package ru.suvorov.weather.domain.`interface`

import ru.suvorov.weather.domain.dto.Weather

interface WeatherService {

    fun getWeatherByCity(city: String): Weather
}