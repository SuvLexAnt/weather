package ru.suvorov.weather.infrastructure.api.openweather.dto

data class Main(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Int,
    val temp_max: Int,
    val temp_min: Int
)