package ru.suvorov.weather.core.port.primary

import ru.suvorov.weather.core.component.use_cases.dto.WeatherAndClothesDTO

interface RecommendationService {

    fun getWeatherAndRecommendationsByCity(city: String, temperatureDiffOrNull: Int?): WeatherAndClothesDTO
}