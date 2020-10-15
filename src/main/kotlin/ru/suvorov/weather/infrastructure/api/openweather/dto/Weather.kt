package ru.suvorov.weather.infrastructure.api.openweather.dto

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)