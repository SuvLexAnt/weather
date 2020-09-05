package ru.suvorov.weather.db.dressing

import ru.suvorov.weather.domain.clothes.SetOfClothes

interface ClothesRepository {

    fun getRecommendationsByParams(snow: Boolean, rain: Boolean, temperature: Double, temperatureDiff: Int): SetOfClothes
}