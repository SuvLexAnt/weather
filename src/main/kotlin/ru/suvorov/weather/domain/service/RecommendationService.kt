package ru.suvorov.weather.domain.service

import ru.suvorov.weather.domain.dto.WeatherAndClothesDTO

interface RecommendationService {

    fun getWeatherAndRecommendationsByCity(city: String, temperatureDiff: Int): WeatherAndClothesDTO
}