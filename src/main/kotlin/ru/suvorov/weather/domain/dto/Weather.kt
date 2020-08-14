package ru.suvorov.weather.domain.dto


data class Weather(
        val locationName: String,
        val temperature: Double,
        val windSpeed: Double,
        val humidity: Int,
        val rain: Boolean,
        val snow: Boolean
)