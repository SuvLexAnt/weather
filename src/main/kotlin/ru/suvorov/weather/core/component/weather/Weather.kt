package ru.suvorov.weather.core.component.weather

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