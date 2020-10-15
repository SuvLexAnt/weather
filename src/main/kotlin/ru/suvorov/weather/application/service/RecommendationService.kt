package ru.suvorov.weather.application.service

import ru.suvorov.weather.application.dto.WeatherAndClothesDTO

interface RecommendationService {

    fun getWeatherAndRecommendationsByCity(city: String, temperatureDiff: Int): WeatherAndClothesDTO
}