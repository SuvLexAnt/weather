package ru.suvorov.weather.domain.service

import ru.suvorov.weather.domain.dto.WeatherAndRecommendations

interface RecommendationService {

    fun getWeatherAndRecommendationsByCity(city: String): WeatherAndRecommendations
}