package ru.suvorov.weather.dressing

import ru.suvorov.weather.domain.dto.Recommendations

interface ClothesRepository {

    fun getRecommendationsByParams(snow: Boolean, rain: Boolean, temperature: Double): Recommendations
}