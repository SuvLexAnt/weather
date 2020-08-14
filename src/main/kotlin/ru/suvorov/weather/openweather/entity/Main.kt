package ru.suvorov.weather.openweather.entity

data class Main(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Int,
    val temp_max: Int,
    val temp_min: Int
)