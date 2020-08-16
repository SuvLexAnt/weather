package ru.suvorov.weather.domain.dto

import java.io.Serializable


data class Weather(
        val locationName: String,
        val temperature: Double,
        val windSpeed: Double,
        val humidity: Int,
        val rain: Boolean,
        val snow: Boolean
): Serializable {
    val serialVersionUID = 1
}