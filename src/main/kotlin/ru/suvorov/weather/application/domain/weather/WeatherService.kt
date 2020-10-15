package ru.suvorov.weather.application.domain.weather

interface WeatherService {

    fun getWeatherByCity(city: String): Weather
}