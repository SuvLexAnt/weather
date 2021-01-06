package ru.suvorov.weather.infrastructure.api.openweather.adapter

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class OpenWeatherUrlComposer(
        @Value("\${openweather.apiKey}") private val apiKey: String,
        @Value("\${openweather.url}") private val url: String,
        @Value("\${openweather.units}") private val units: String
) {

    fun getWeatherPredictionUrlByCity(city: String, hideKey: Boolean = false): String
            = "${url}q=$city&units=$units&appid=${if (hideKey) "API_KEY" else apiKey}"
}