package ru.suvorov.weather.domain.dto

data class WeatherAndRecommendations(
        val weather: Weather,
        val recommendations: Recommendations
)