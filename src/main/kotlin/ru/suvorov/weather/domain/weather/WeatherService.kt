package ru.suvorov.weather.domain.weather

interface WeatherService {

    fun getWeatherByCity(city: String): Weather
}