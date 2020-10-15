package ru.suvorov.weather.infrastructure.api.openweather.dto

data class Sys(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)