package ru.suvorov.weather.core.port.secondary

import ru.suvorov.weather.core.component.weather.Weather

interface WeatherFacade {

    /**
     * Returns weather conditions by name of geographical place
     * @param city - name of city or a place
     */
    fun getWeatherByCity(city: String): Weather
}